/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosade.generator;

import java.util.ArrayList;
import tosade.controller.Generator;
import tosade.domain.*;
import tosade.template.KeyValue;

/**
 *
 * @author Jelle
 */
public class Trigger {
    KeyValue kv;
            
    public Trigger() {
        
    }
    
    public String generateTriger(TargetSchema targetSchema, SchemaTable schemaTable) {
        String trigger = "";
        ArrayList<SchemaTableField> schemaTablesField = Generator.toolDatabase.fetchSchemaTableFields(schemaTable.id);
        for (SchemaTableField schemaTableField : schemaTablesField) {
            ArrayList<BusinessRule> businessRules = Generator.toolDatabase.fetchBusinessRules(schemaTableField.id);
            for (BusinessRule businessRule : businessRules) {
                BusinessRuleType businessRuleType = Generator.toolDatabase.fetchBusinessRuleType(businessRule.type_id);
                BusinessContext context = new BusinessContext(businessRuleType.systemclass);
                trigger = trigger + context.getTrigger();
            }
        }


        ArrayList<KeyValue> kvList = new ArrayList<>();
        kvList.add(new KeyValue("schemaCode",targetSchema.code));
        kvList.add(new KeyValue("tableCode",schemaTable.code));
        kvList.add(new KeyValue("tableName",schemaTable.name));
        kvList.add(new KeyValue("triggers",trigger));
        return Generator.context.getTemplate("trigger", kvList);
    }
}
