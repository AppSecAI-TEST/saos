package pl.edu.icm.saos.importer.notapi.common;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.powermock.reflect.Whitebox;
import org.springframework.batch.item.ExecutionContext;

import pl.edu.icm.saos.persistence.model.importer.notapi.RawSourceScJudgment;
import pl.edu.icm.saos.persistence.repository.RawSourceJudgmentRepository;

import com.google.common.collect.Lists;


/**
 * @author Łukasz Dumiszewski
 */

public class JudgmentImportProcessReaderTest {

    private JudgmentImportProcessReader<RawSourceScJudgment> scjImportProcessReader = new JudgmentImportProcessReader<>(RawSourceScJudgment.class);
    
    private RawSourceJudgmentRepository rawJudgmentRepository = Mockito.mock(RawSourceJudgmentRepository.class);
    
    
    @Before
    public void before() {
        scjImportProcessReader.setRawSourceJudgmentRepository(rawJudgmentRepository);
    }
    
    
    
    @Test
    public void open() {
        
        // given
        
        List<Long> rJudgmentIds = Lists.newArrayList(12l, 123l, 45l);
        when(rawJudgmentRepository.findAllNotProcessedIds(RawSourceScJudgment.class)).thenReturn(rJudgmentIds);
        
        
        // execute
        
        scjImportProcessReader.open(Mockito.mock(ExecutionContext.class));
        
        
        
        // assert
        
        List<Long> internalRJudgmentIds = Whitebox.getInternalState(scjImportProcessReader, "rJudgmentIds");
        
        verify(rawJudgmentRepository).findAllNotProcessedIds(RawSourceScJudgment.class);
        verifyNoMoreInteractions(rawJudgmentRepository);
        
        assertEquals(rJudgmentIds, internalRJudgmentIds);
        
    }
    
    
    
    @Test
    public void read() throws Exception {
        
        // given
        
        List<Long> rJudgmentIds = Lists.newArrayList(12l, 123l, 45l);
        Whitebox.setInternalState(scjImportProcessReader, "rJudgmentIds", new LinkedList<Long>(rJudgmentIds));
        
        RawSourceScJudgment rJudgment0 = createSimpleRawSourceScJudgment(rJudgmentIds.get(0));
        RawSourceScJudgment rJudgment1 = createSimpleRawSourceScJudgment(rJudgmentIds.get(1));
        RawSourceScJudgment rJudgment2 = createSimpleRawSourceScJudgment(rJudgmentIds.get(2));

        when(rawJudgmentRepository.findOne(rJudgmentIds.get(0), RawSourceScJudgment.class)).thenReturn(rJudgment0);
        when(rawJudgmentRepository.findOne(rJudgmentIds.get(1), RawSourceScJudgment.class)).thenReturn(rJudgment1);
        when(rawJudgmentRepository.findOne(rJudgmentIds.get(2), RawSourceScJudgment.class)).thenReturn(rJudgment2);
        
     
        
        // execute & assert
        
        readAndAssert(rJudgment0);
        readAndAssert(rJudgment1);
        readAndAssert(rJudgment2);
        readAndAssert(null);
        
        
    }


     
    
    
    
    //------------------------ PRIVATE --------------------------

    
    private void readAndAssert(RawSourceScJudgment expectedRawJudgment) throws Exception {
        
        // execute (1)
        RawSourceScJudgment readRJudgment = scjImportProcessReader.read();
        
        // assert
        if (expectedRawJudgment == null) {
            assertNull(readRJudgment);
        } else {
            assertEquals(expectedRawJudgment.getId(), readRJudgment.getId());
        }
    }
    
    private RawSourceScJudgment createSimpleRawSourceScJudgment(long rJudgmentId) {
        RawSourceScJudgment rJudgment = new RawSourceScJudgment();
        rJudgment.setJsonContent(""+rJudgmentId);
        Whitebox.setInternalState(rJudgment, "id", rJudgmentId);
        return rJudgment;
        
    }
    
}
