package br.com.zup.nossobancodigital.core.storage;

import br.com.zup.nossobancodigital.domain.service.FotoStorageService;
import br.com.zup.nossobancodigital.infrastructure.service.storage.LocalFotoStorageService;
import br.com.zup.nossobancodigital.infrastructure.service.storage.S3FotoStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StorageConfig {

    @Autowired
    private br.com.zup.nossobancodigital.core.storage.StorageProperties storageProperties;

//    @Bean
//    @ConditionalOnProperty(name = "nossobancodigital.storage.tipo", havingValue = "s3")
//    public AmazonS3 amazonS3() {
//        var credentials = new BasicAWSCredentials(
//                storageProperties.getS3().getIdChaveAcesso(),
//                storageProperties.getS3().getChaveAcessoSecreta());
//
//        return AmazonS3ClientBuilder.standard()
//                .withCredentials(new AWSStaticCredentialsProvider(credentials))
//                .withRegion(storageProperties.getS3().getRegiao())
//                .build();
//    }

    @Bean
    public FotoStorageService fotoStorageService() {
        if (br.com.zup.nossobancodigital.core.storage.StorageProperties.TipoStorage.S3.equals(storageProperties.getTipo())) {
            return new S3FotoStorageService();
        } else {
            return new LocalFotoStorageService();
        }
    }

}