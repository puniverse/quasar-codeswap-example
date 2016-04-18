# Qusar Hot Code Swapping Example

## Building

To build the main program:

```sh
./gradlew :quasar-codeswap-main:build
```

To build the module:

```sh
./gradlew :quasar-codeswap-main:jar
```

## Running

```sh
./gradlew :quasar-codeswap-main:run
```

Then, while the program is running in one terminal, do the following in another:

```sh
cp quasar-codeswap-module/build/libs/quasar-codeswap-module.jar modules
```

## License

This project is released under the [MIT license](http://opensource.org/licenses/MIT).
Copyright (c) 2014-2016 Parallel Universe
