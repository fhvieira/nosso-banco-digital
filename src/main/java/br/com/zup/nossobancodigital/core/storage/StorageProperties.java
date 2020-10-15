package br.com.zup.nossobancodigital.core.storage;

import com.amazonaws.regions.Regions;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.nio.file.Path;

@Getter
@Setter
@Component
@ConfigurationProperties("nossobancodigital.storage")
public class StorageProperties {

    private Local local = new Local();
    private S3 s3 = new S3();
    private TipoStorage tipo = TipoStorage.LOCAL;

    public enum TipoStorage {
        LOCAL, S3
    }

    @Getter
    @Setter
    public class Local {
        Path diretorioFotos;
    }

    @Getter
    @Setter
    public class S3 {
        private String idChaveAcess;
        private String chaveAcessoSecreta;
        private String bucket;
        private Regions regiao;
        private String diretorio;
    }
}
