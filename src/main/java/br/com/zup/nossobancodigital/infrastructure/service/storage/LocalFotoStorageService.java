package br.com.zup.nossobancodigital.infrastructure.service.storage;

import br.com.zup.nossobancodigital.core.storage.StorageProperties;
import br.com.zup.nossobancodigital.domain.service.FotoStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class LocalFotoStorageService implements FotoStorageService {

    @Autowired
    StorageProperties storageProperties;

    @Value("${nossobancodigital.storage.local.diretorio-fotos}")
    private Path diretorioFotos;

    @Override
    public FotoRecuperada recuperar(String nomeArquivo) {
        try {
            Path arquivoPath = getArquivoPath(nomeArquivo);

            var fotoRecuperada = FotoRecuperada.builder()
                    .inputStream(Files.newInputStream(arquivoPath))
                    .build();

            return fotoRecuperada;
        } catch (IOException e) {
            throw new br.com.zup.nossobancodigital.infrastructure.service.storage.StorageException("nao foi possivel recuperar o arquivo", e);
        }
    }

    @Override
    public void remover(String nomeArquivo) {
        Path arquivoPath = getArquivoPath(nomeArquivo);

        try {
            Files.deleteIfExists(arquivoPath);
        } catch (IOException e) {
            throw new br.com.zup.nossobancodigital.infrastructure.service.storage.StorageException("nao foi possivel excluir arquivo");
        }
    }

    @Override
    public void armazenar(NovaFoto novaFoto) {

        try {
            var arquivoPath = getArquivoPath(novaFoto.getNomeArquivo());

            FileCopyUtils.copy(novaFoto.getInputStream(), Files.newOutputStream(arquivoPath));
        } catch (IOException e) {
            throw new br.com.zup.nossobancodigital.infrastructure.service.storage.StorageException("nao foi possivel armazenar arquivo", e);
        }
    }

    private Path getArquivoPath(String nomeArquivo) {
        return storageProperties.getLocal().getDiretorioFotos()
                .resolve(Path.of(nomeArquivo));
    }
}
