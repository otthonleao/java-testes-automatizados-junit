# Testes Unitários com JUnit 5

Neste respositório são abordados os testes unitários em uma aplicação Java utilizando o JUnit 5. Além disso, é utilizado outras ferramentas para nos ajudar na escrita dos testes, como por exemplo:
1. **Mockito** para nos ajudar na criação de Mocks
2. **Jacoco** que é uma ferramenta para gerar relatórios de cobertura de testes.


Dentre várias outras coisas, o objetivo é:

- [x] Criar a estrutura de arquivos para os testes;
- [x] Montar diferentes cenários de testes;
- [x] Aplicar a metodologia de TDD no desenvolvimento de suas aplicações;
- [x] Testar exceções de regra de negócio;
- [x] Entender o ciclo de execução dos testes;
- [x] Utilizar o padrão data builder para prover dados para os seus cenários de testes;
- [x] Utilizar o Jacoco para criar relatórios de cobertura de testes.

## JUnit 5
É um framework de testes unitários popular para Java que ajuda a escrever testes unitários de forma mais fácil.
```xml
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter</artifactId>
    <version>5.10.2</version>
</dependency>
```
- **Anotações:** Usa anotações como `@Test` e `@ExtendWith` para marcar métodos de teste e extensões do JUnit, respectivamente.
- **Assertions:** Fornece um conjunto abrangente de métodos de `assertion` para verificar os resultados dos testes.
- **Suporte a testes parametrizados:** Permite escrever testes parametrizados, que executam o mesmo teste com diferentes conjuntos de dados. Isso pode tornar os testes unitários mais concisos e reutilizáveis.

## Mockito
É um framework que permite a criação de objetos simulados, chamados de "mocks", que imitam o comportamento de classes dependentes. 
Mockito é útil para isolar a unidade de código que você está testando de suas dependências. Isso torna seus testes unitários mais rápidos, confiáveis e fáceis de manter.
```xml
<dependency>
    <groupId>org.mockito</groupId>
    <artifactId>mockito-core</artifactId>
    <version>5.11.0</version>
</dependency>
<dependency>
    <groupId>org.mockito</groupId>
    <artifactId>mockito-junit-jupiter</artifactId>
    <version>5.11.0</version>
</dependency>
```

#### Alguns casos onde o Mockito é útil:

- **Simular classes externas:** Imagine que sua classe depende de um banco de dados. Você não precisa realmente se conectar a um banco de dados durante o teste unitário. Com Mockito, você pode criar um mock da classe de banco de dados que retorna dados predefinidos.
- **Verificar se os métodos de uma dependência foram chamados:** Você pode usar Mockito para verificar se os métodos de uma dependência foram chamados com os argumentos corretos. Isso ajuda a garantir que sua classe está interagindo com suas dependências da maneira correta.
- **Testar diferentes comportamentos de dependências:** Você pode configurar mocks para retornar diferentes valores ou lançar exceções. Isso permite testar sua classe em diferentes cenários.

## Jacoco
É uma biblioteca que auxilia na medição da cobertura de teste (porcentagem do seu código fonte que é realmente executada durante os testes unitários). Uma alta cobertura de teste indica que a maioria do seu código foi testado e há menos chances de bugs.
```xml
<dependency>
    <groupId>org.jacoco</groupId>
    <artifactId>jacoco-maven-plugin</artifactId>
    <version>0.8.12</version>
    <executions>
        <execution>
            <goals>
                <goal>prepare-agent</goal>
            </goals>
        </execution>
        <execution>
            <id>report</id>
            <phase>test</phase>
            <goals>
                <goal>report</goal>
            </goals>
            <configuration>
                <excludes>
                    <exclude>**/models/**</exclude> <!--Qualquer coisa que estiver no Models, não vai ser verificada pelo Jacoco-->
                    <exclude>**/JavaTestesAutomatizadosJunitApplication.class</exclude>
                </excludes>
            </configuration>
        </execution>
    </executions>
</dependency>
```

1. Durante a execução dos testes unitários, o JaCoCo registra quais linhas de código foram executadas e quais não foram.
2. Ele gera relatórios detalhados que mostram a cobertura de teste por classe, método e linha de código.
3. Esses relatórios ajudam na identificação das partes do seu código que não estão sendo testadas e que precisam de mais atenção.

#### Benefícios de usar o JaCoco:

- **Melhora a qualidade do código:** Ao identificar partes do código não cobertas por testes, o JaCoCo ajuda a escrever testes unitários mais abrangentes, o que pode levar a um código mais estável e livre de bugs.
- **Fornece métricas para gerenciamento de código:** Os relatórios de cobertura de teste do JaCoCo podem ser usados para monitorar a qualidade do código ao longo do tempo e definir metas para a cobertura de teste.
