package com.system.springboot.Configuration;

import org.flywaydb.core.Flyway;
import org.springframework.boot.autoconfigure.flyway.FlywayProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
@EnableConfigurationProperties({FlywayProperties.class})
public class DatabaseConfig {

    //CONFIGURAÇÃO DO FLYWAY PARA MIGRATION
    //CASO NÃO EXISTA A TABELA DEFINIDA NO UserController ELE SERÁ O RESPONSAVEL POR CRIAR
    @Bean(initMethod = "migrate")
    public Flyway flyway(FlywayProperties flywayProperties) {
        //ENDEREÇO PARA O BANCO DE DADOS
        flywayProperties.setUrl("jdbc:mysql://localhost:3306/mydb");
        return Flyway.configure()
                .dataSource(flywayProperties.getUrl(),
                        // NOME DO USUARIO DO BANCO DE DADOS
                        "root",
                        // SENHA PARA O USUARIO DO BANCO DE DADOS
                        "root")
                .locations(flywayProperties.getLocations().toArray(String[]::new))
                .baselineOnMigrate(true)
                .load();
    }


    //CONFIGURAÇÃO PARA O GERAÇÃO DO DATASOURCE
    @Primary
    @Bean
    public DataSource primaryDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        //ENDEREÇO DO BANCO DE DADOS
        dataSourceBuilder.url("jdbc:mysql://localhost:3306/mydb");
        //NOME DE USUARIO
        dataSourceBuilder.username("root");
        //SENHA
        dataSourceBuilder.password("root");
        return dataSourceBuilder.build();
    }
}
