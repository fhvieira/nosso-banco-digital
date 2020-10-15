package br.com.zup.nossobancodigital.domain.service;

import lombok.Builder;
import lombok.Getter;

import java.io.InputStream;
import java.util.UUID;

public interface FotoStorageService {

    void armazenar(NovaFoto novaFoto);

    FotoRecuperada recuperar(String nomeArquivo);

    default void substituir(NovaFoto novaFoto, String nomeArquivoAntigo) {
        this.armazenar(novaFoto);

        if (!nomeArquivoAntigo.isBlank()) {
            this.remover(nomeArquivoAntigo);
        }
    }

    void remover(String nomeArquivo);

    default String gerarNomeArquivo(String nomeArquivoOriginal) {
        return UUID.randomUUID().toString() + "_" + nomeArquivoOriginal;
    }

    @Builder
    @Getter
    class NovaFoto {

        private String nomeArquivo;
        private String contentType;
        private InputStream inputStream;
    }

    @Builder
    @Getter
    class FotoRecuperada {
        private InputStream inputStream;
        private String url;

        public boolean temUrl() {
            return url != null;
        }

        public boolean temInputStream() {
            return  inputStream != null;
        }
    }
}
