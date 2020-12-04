package com.example.micronault.greetings.representations;

import io.micronaut.http.hateoas.Link;
import io.micronaut.http.hateoas.Resource;

import java.net.URI;
import java.net.URISyntaxException;

public class ResourceBuilder<Impl extends Resource> {

    private Impl resource;

    private Link link;

    public static <clazz extends Resource> ResourceBuilder<clazz> aResourceBuilder(Class<?> clazz) {
        return new ResourceBuilder<clazz>();
    }

    public ResourceBuilder withResource(final Impl resource) {
        this.resource = resource;
        return this;
    }

    public ResourceBuilder withLink(final String link) throws URISyntaxException {
        this.link = (Link) Link.build(new URI(link));
        return this;
    }

    public ResourceBuilder withLink(final Link link) {
        this.link = link;
        return this;
    }

    public GreetingResource<Impl> build() {
        return new GreetingResource<Impl>()
            .embedded(resource.getClass().getSimpleName().toLowerCase(), resource)
            .link("self", link);
    }
}
