package com.example.micronault.greetings.representations;

import io.micronaut.http.hateoas.Link;
import io.micronaut.http.hateoas.Resource;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

public class ListResourceBuilder<Impl extends Resource> {

    private List<Impl> resources;

    private Link link;

    public static <clazz extends Resource> ListResourceBuilder<clazz> aListResourceBuilder(Class<?> clazz) {
        return new ListResourceBuilder<clazz>();
    }

    public ListResourceBuilder withResources(final List<Impl> resources) {
        this.resources = resources;
        return this;
    }

    public ListResourceBuilder withLink(final String link) throws URISyntaxException {
        this.link = (Link) Link.build(new URI(link));
        return this;
    }

    public ListResourceBuilder withLink(final Link link) {
        this.link = link;
        return this;
    }

    public Impl build() {
        return (Impl) new GreetingResource()
            .embedded(resources.getClass().getSimpleName().toLowerCase(), (Resource) resources)
            .link("self", link);
    }
}
