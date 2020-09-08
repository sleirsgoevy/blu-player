package org.davic.resources;

public interface ResourceClient
{
    boolean requestRelease(ResourceProxy paramResourceProxy, Object paramObject);
    void release(ResourceProxy paramResourceProxy);
    void notifyRelease(ResourceProxy paramResourceProxy);
}
