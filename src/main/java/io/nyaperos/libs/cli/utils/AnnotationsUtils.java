package io.nyaperos.libs.cli.utils;

import io.nyaperos.libs.cli.commons.InvalidClassInstantiationException;
import org.reflections.Reflections;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import java.lang.annotation.Annotation;
import java.util.Set;

public class AnnotationsUtils {

    private AnnotationsUtils() {
        throw new InvalidClassInstantiationException(AnnotationsUtils.class);
    }

    public static Set<Class<?>> find(Package pkg, Class<? extends Annotation> annotation) {
        Reflections reflections = new Reflections(
                new ConfigurationBuilder()
                        .setUrls(ClasspathHelper.forPackage(pkg.getName()))
                        .filterInputsBy(new FilterBuilder()
                                .include(FilterBuilder.prefix(pkg.getName()))
                        )
                        .setScanners(new TypeAnnotationsScanner())
        );
        return reflections.getTypesAnnotatedWith(annotation, true);
    }
}
