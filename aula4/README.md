# olá-mundo

Este projeto usa Quarkus, o Framework Java Supersônico e Subatômico.

Se você quiser aprender mais sobre o Quarkus, por favor visite o site: <https://quarkus.io/>.

## Executando a aplicação em modo de desenvolvimento

Você pode executar sua aplicação em modo de desenvolvimento, que habilita o live coding, usando:

Para Linux/macOS:
```shell script
./mvnw quarkus:dev
```

Para Windows:
```shell script
mvnw.cmd quarkus:dev
```

> **_NOTA:_** O Quarkus agora vem com uma Dev UI, que está disponível apenas no modo de desenvolvimento em <http://localhost:8080/q/dev/>.

## Empacotando e executando a aplicação

A aplicação pode ser empacotada usando:

Para Linux/macOS:
```shell script
./mvnw package
```

Para Windows:
```shell script
mvnw.cmd package
```

Isso produz o arquivo `quarkus-run.jar` no diretório `target/quarkus-app/`.
Esteja ciente de que não é um _über-jar_, pois as dependências são copiadas para o diretório `target/quarkus-app/lib/`.

A aplicação agora pode ser executada usando `java -jar target/quarkus-app/quarkus-run.jar`.

Se você quiser construir um _über-jar_, execute o seguinte comando:

Para Linux/macOS:
```shell script
./mvnw package -Dquarkus.package.jar.type=uber-jar
```

Para Windows:
```shell script
mvnw.cmd package -Dquarkus.package.jar.type=uber-jar
```

A aplicação, empacotada como um _über-jar_, agora pode ser executada usando `java -jar target/*-runner.jar`.

## Criando um executável nativo

Você pode criar um executável nativo usando:

Para Linux/macOS:
```shell script
./mvnw package -Dnative
```

Para Windows:
```shell script
mvnw.cmd package -Dnative
```

Ou, se você não tem o GraalVM instalado, você pode construir o executável nativo em um contêiner usando:

Para Linux/macOS:
```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

Para Windows:
```shell script
mvnw.cmd package -Dnative -Dquarkus.native.container-build=true
```

Você pode então executar seu executável nativo com: `./target/hello-world-1.0.0-SNAPSHOT-runner`

Se você quiser aprender mais sobre a construção de executáveis nativos, por favor consulte <https://quarkus.io/guides/maven-tooling>.

## Guias Relacionados

- REST ([guia](https://quarkus.io/guides/rest)): Uma implementação Jakarta REST utilizando processamento em tempo de compilação e Vert.x. Esta extensão não é compatível com a extensão quarkus-resteasy, ou qualquer uma das extensões que dependem dela.

## Código Fornecido

### REST

Inicie facilmente seus Web Services REST

[Seção do guia relacionada...](https://quarkus.io/guides/getting-started-reactive#reactive-jax-rs-resources)
