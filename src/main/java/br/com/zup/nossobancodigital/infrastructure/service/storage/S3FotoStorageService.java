package br.com.zup.nossobancodigital.infrastructure.service.storage;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import br.com.zup.nossobancodigital.core.storage.StorageProperties;
import br.com.zup.nossobancodigital.domain.service.FotoStorageService;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;

public class S3FotoStorageService implements FotoStorageService {

    @Autowired
    AmazonS3 amazonS3;

    @Autowired
    StorageProperties storageProperties;

    private String getCaminhoNomeArquivo(String nomeArquivo) {
        return String.format("%s/%s", storageProperties.getS3().getDiretorio(), nomeArquivo);
    }

    @Override
    public void armazenar(NovaFoto novaFoto) {
        try {
            String caminhoArquivo = getCaminhoNomeArquivo(novaFoto.getNomeArquivo());

            var objectMetaData = new ObjectMetadata();

            objectMetaData.setContentType(novaFoto.getContentType());

            var putObjectRequest = new PutObjectRequest(
                    storageProperties.getS3().getBucket(),
                    caminhoArquivo,
                    novaFoto.getInputStream(),
                    objectMetaData)
                .withCannedAcl(CannedAccessControlList.PublicRead);

            amazonS3.putObject(putObjectRequest);
        } catch (Exception e) {
            throw new br.com.zup.nossobancodigital.infrastructure.service.storage.StorageException("nao foi possivel enviar arquivo para amazon S3", e);
        }
    }

    @Override
    public FotoRecuperada recuperar(String nomeArquivo) {
        String caminhoArquivo = getCaminhoNomeArquivo(nomeArquivo);

        URL url = amazonS3.getUrl(storageProperties.getS3().getBucket(), caminhoArquivo);

        return FotoRecuperada.builder()
                .url(url.toString())
                .build();
    }

    @Override
    public void remover(String nomeArquivo) {
        try {
            String caminhoarquivo = getCaminhoNomeArquivo(nomeArquivo);

            var deleteObjectRequest = new DeleteObjectRequest(
                    storageProperties.getS3().getBucket(), nomeArquivo);

            amazonS3.deleteObject(deleteObjectRequest);
        } catch(Exception e) {
            throw new br.com.zup.nossobancodigital.infrastructure.service.storage.StorageException("nao foi possivel excluir arquivo na amazon S3", e);
        }
    }
}
