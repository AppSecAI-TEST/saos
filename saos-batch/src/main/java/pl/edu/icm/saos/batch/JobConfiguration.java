package pl.edu.icm.saos.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

import pl.edu.icm.saos.importer.commoncourt.court.CcImportJobExecutionListener;
import pl.edu.icm.saos.importer.commoncourt.court.CommonCourtImportProcessor;
import pl.edu.icm.saos.importer.commoncourt.court.CommonCourtImportWriter;
import pl.edu.icm.saos.importer.commoncourt.court.XmlCommonCourt;
import pl.edu.icm.saos.importer.commoncourt.download.CcjImportDownloadProcessor;
import pl.edu.icm.saos.importer.commoncourt.download.CcjImportDownloadReader;
import pl.edu.icm.saos.importer.commoncourt.download.CcjImportDownloadWriter;
import pl.edu.icm.saos.importer.commoncourt.download.SourceCcJudgmentTextData;
import pl.edu.icm.saos.importer.commoncourt.process.CcjImportProcessProcessor;
import pl.edu.icm.saos.importer.commoncourt.process.CcjImportProcessReader;
import pl.edu.icm.saos.importer.commoncourt.process.CcjImportProcessSkipListener;
import pl.edu.icm.saos.importer.commoncourt.process.CcjImportProcessSkipPolicy;
import pl.edu.icm.saos.importer.commoncourt.process.CcjImportProcessStepExecutionListener;
import pl.edu.icm.saos.importer.commoncourt.process.CcjImportProcessWriter;
import pl.edu.icm.saos.persistence.model.CommonCourt;
import pl.edu.icm.saos.persistence.model.CommonCourtJudgment;
import pl.edu.icm.saos.persistence.model.importer.RawSourceCcJudgment;


@Configuration
@ComponentScan
public class JobConfiguration {

    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private StepBuilderFactory steps;

    
    //======== Common Court Judgment importer ========
    
    @Scope(value="step", proxyMode = ScopedProxyMode.TARGET_CLASS)
    @Bean
    public CcjImportDownloadReader ccjImportDownloadReader() {
        return new CcjImportDownloadReader();
    }
    
    @Autowired
    private CcjImportDownloadWriter ccjImportDownloadWriter;
    
    @Autowired
    private CcjImportDownloadProcessor ccjImportDownloadProcessor;
    
    @Autowired
    private CcjImportProcessReader ccjImportProcessReader;
    
    @Autowired
    private CcjImportProcessProcessor ccjImportProcessProcessor;
    
    @Autowired
    private CcjImportProcessWriter ccjImportProcessWriter;
    
    @Autowired
    private CcjImportProcessStepExecutionListener ccjImportProcessStepExecutionListener;
    
    @Autowired
    private CcjImportProcessSkipListener ccjImportProcessSkipListener;
    
    @Autowired
    private CcjImportProcessSkipPolicy ccjImportProcessSkipPolicy;
    
    
    @Bean
    public Job ccJudgmentImportJob() {
        return jobs.get("ccJudgmentImportJob").start(ccJudgmentImportDownloadStep()).next(ccJudgmentImportProcessStep()).incrementer(new RunIdIncrementer()).build();
    }

    
    
    @Bean
    public Job ccJudgmentImportDownloadJob() {
        return jobs.get("ccJudgmentImportDownloadJob").start(ccJudgmentImportDownloadStep()).incrementer(new RunIdIncrementer()).build();
    }
    
    
    @Bean
    public Job ccJudgmentImportProcessJob() {
        return jobs.get("ccJudgmentImportProcessJob").start(ccJudgmentImportProcessStep()).incrementer(new RunIdIncrementer()).build();
    }
    
    
    
    @Bean
    @Autowired
    protected Step ccJudgmentImportDownloadStep() {
        return steps.get("ccJudgmentImportDownloadStep").<SourceCcJudgmentTextData, RawSourceCcJudgment> chunk(20)
            .reader(ccjImportDownloadReader())
            .processor(ccjImportDownloadProcessor)
            .writer(ccjImportDownloadWriter)
            .build();
    } 
    
    @Bean
    @Autowired
    protected Step ccJudgmentImportProcessStep() {
        return steps.get("ccJudgmentImportProcessStep")
             .<RawSourceCcJudgment, CommonCourtJudgment> chunk(20)
                 .faultTolerant().skipPolicy(ccjImportProcessSkipPolicy).listener(ccjImportProcessSkipListener)
            .reader(ccjImportProcessReader)
            .processor(ccjImportProcessProcessor)
            .writer(ccjImportProcessWriter)
            .listener(ccjImportProcessStepExecutionListener)
            .build();
    } 
    
    
    
    //======== Common Court importer ========
    
    @Autowired
    private CommonCourtImportProcessor ccImportProcessor;
    
    @Autowired
    private CommonCourtImportWriter ccImportWriter;
    
    @Autowired
    private StaxEventItemReader<XmlCommonCourt> ccImportReader;

    @Autowired
    private CcImportJobExecutionListener ccImportJobExecutionListener;
    
   
    @Bean
    public Job commonCourtImportJob() {
        return jobs.get("commonCourtImportJob").start(commonCourtImportProcessStep()).listener(ccImportJobExecutionListener).incrementer(new RunIdIncrementer()).build();
    }

    
    @Bean
    @Autowired
    protected Step commonCourtImportProcessStep() {
        return steps.get("commonCourtImportStep").<XmlCommonCourt, CommonCourt> chunk(20)
            .reader(ccImportReader)
            .processor(ccImportProcessor)
            .writer(ccImportWriter)
            .build();
    } 
    

}