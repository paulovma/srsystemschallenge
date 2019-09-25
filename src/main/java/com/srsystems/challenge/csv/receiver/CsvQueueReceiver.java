package com.srsystems.challenge.csv.receiver;

import com.srsystems.challenge.city.domain.CityRepository;
import com.srsystems.challenge.csv.city.batch.BatchImportConfig;
import com.srsystems.challenge.csv.city.factory.CityRequestFactory;
import com.srsystems.challenge.entity.City;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Component
public class CsvQueueReceiver {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    @Qualifier(BatchImportConfig.BATCH_IMPORT_JOB)
    private Job job;

    @Autowired
    private CityRequestFactory cityRequestFactory;

    private CityRepository cityRepository;

    public CsvQueueReceiver(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }


    @RabbitListener(queues = {"test_queue"})
    //o rollback eh feito automaticamente para as exceptions "unchecked" (RunTimeException)
    //quando tempos uma exception "checked" (Exception) , o rollback automatico nao acontece, entao precisamos explicitar para quais queremos que seja feito o rollback
//    @Transactional(rollbackFor = IOException.class)
    public void receiveMessage(byte[] message) throws IOException {
        JobParameters params = new JobParametersBuilder()
                .addLong("timestamp", System.currentTimeMillis())
                .addString("fileName", new String(message))
                .toJobParameters();

        try {
            jobLauncher.run(job, params);
        } catch (JobExecutionAlreadyRunningException e) {
            e.printStackTrace();
        } catch (JobRestartException e) {
            e.printStackTrace();
        } catch (JobInstanceAlreadyCompleteException e) {
            e.printStackTrace();
        } catch (JobParametersInvalidException e) {
            e.printStackTrace();
        }

//        List<City> cities = cityRequestFactory.make(new String(message));
//        cityRepository.saveAll(cities);
    }

}
