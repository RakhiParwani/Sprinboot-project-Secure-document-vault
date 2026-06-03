//package net.engineeringdigest.journalApp.service;
//
//import org.junit.jupiter.api.extension.ExtensionContext;
//import org.junit.jupiter.params.provider.Arguments;
//import org.junit.jupiter.params.provider.ArgumentsProvider;
//
//import java.util.stream.Stream;
//
//class MyProvider implements ArgumentsProvider {
//
//    @Override
//    public Stream<Arguments> provideArguments(ExtensionContext context) {
//
//        return Stream.of(
//                Arguments.of(2),
//                Arguments.of(4),
//                Arguments.of(6)
//        );
//    }
//}