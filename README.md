SIEVE Cache in Java
===================

SIEVE is simpler than LRU.
              
# Get started

```
   Cache<String> cache = new SieveCache<>();
   cache.put("nick", "Jackie");
   System.out.println(cache.get("nick"));
```

# References

* SIEVE is simpler than LRU: https://cachemon.github.io/SIEVE-website/blog/2023/12/17/sieve-is-simpler-than-lru/