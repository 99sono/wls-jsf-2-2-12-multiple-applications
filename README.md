# Weblogic issue - JSF 2.2.12 - Application with webservice context will get - Multiple JSF Applications found on same ClassLoader.

JSF 2.2-12 returns:
Multiple JSF Applications found on same ClassLoader.  Unable to safely determine which FactoryManager instance to use. Defaulting to first match.

PROBLEM:
An application with JSF content, e.g. an index.xhtml and a @WebService, will trigger the warning:

```
  private void initFromFactoryMap(ClassLoader cl,
                Map<FactoryManagerCacheKey,FactoryFinderInstance> factoryMap) {
            // We don't have a FacesContext.
            // Our only recourse is to inspect the keys of the
            // factoryMap and see if any of them has a classloader
            // equal to our argument cl.
            Set<FactoryManagerCacheKey> keys = factoryMap.keySet();
            FactoryManagerCacheKey matchingKey = null;
            
            if (keys.isEmpty()) {
                this.cl = cl;
                this.marker = new Long(System.currentTimeMillis());
            } else {
            
                // For each entry in the factoryMap's keySet...
                for (FactoryManagerCacheKey currentKey : keys) {
                    assert(null != currentKey.cl);
                    ClassLoader matchingCL = findMatchConsideringParentClassLoader(cl, currentKey.cl);
                    // If there is a match...
                    if (null != matchingCL) {
                        // If the match was found on a previous iteration...
                        if (null != matchingKey) {
                            LOGGER.log(Level.WARNING, "Multiple JSF Applications found on same ClassLoader.  Unable to safely determine which FactoryManager instance to use. Defaulting to first match.");
                            break;
                        }
                        matchingKey = currentKey;
                        this.cl = matchingCL;
                    }
                }
                if (null != matchingKey) {
                    this.marker = matchingKey.marker;
                    this.context = matchingKey.context;
                }
            }
            
        }
```
		
DEBUGING:
It is possible to see that there are no two implementations but two contexts.
E.g. 
(a)	ServletContext@636573046[app:wls-jsf-2-2-12-multiple-applications module:wls-jsf-2-2-12-multiple-applications path:/SomeWebService spec-version:3.1]
(b)	ServletContext@1295917287[app:wls-jsf-2-2-12-multiple-applications module:wls-jsf-2-2-12-multiple-applications path:/wls-jsf-2-2-12-multiple-applications spec-version:3.1]


NOTE:
If we go to the coide and remove the "reproducebug.SomeWebService" class from the package, the issue goes way.
