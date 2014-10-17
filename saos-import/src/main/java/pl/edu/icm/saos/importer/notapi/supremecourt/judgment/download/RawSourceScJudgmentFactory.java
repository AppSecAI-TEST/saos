package pl.edu.icm.saos.importer.notapi.supremecourt.judgment.download;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.edu.icm.saos.importer.notapi.supremecourt.judgment.json.SourceScJudgment;
import pl.edu.icm.saos.importer.notapi.supremecourt.judgment.json.SourceScJudgmentParser;
import pl.edu.icm.saos.persistence.model.importer.notapi.RawSourceScJudgment;

/**
 * @author Łukasz Dumiszewski
 */
@Service("rawSourceScJudgmentFactory")
class RawSourceScJudgmentFactory {

    
    private SourceScJudgmentParser sourceScJudgmentParser;
    
    
    
    //------------------------ LOGIC --------------------------
    
    public RawSourceScJudgment createRawSourceScJudgment(String jsonContent) {
        RawSourceScJudgment judgment = new RawSourceScJudgment();
        judgment.setJsonContent(jsonContent);
        SourceScJudgment sourceScJudgment = sourceScJudgmentParser.parse(jsonContent);
        judgment.setMultiChambers(sourceScJudgment.getSupremeCourtChambers().size()>1);
        judgment.setSourceId(sourceScJudgment.getSource().getSourceJudgmentId());
        return judgment;
    }


    
    //------------------------ PRIVATE --------------------------
    
    @Autowired
    public void setSourceScJudgmentParser(SourceScJudgmentParser sourceScJudgmentParser) {
        this.sourceScJudgmentParser = sourceScJudgmentParser;
    }
    
}