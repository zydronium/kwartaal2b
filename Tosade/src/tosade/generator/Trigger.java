/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosade.generator;

import java.util.ArrayList;
import tosade.domain.*;
import tosade.template.Context;
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
        ArrayList<SchemaTableField> schemaTablesFields = ToolDatabase.getInstance().fetchSchemaTableFields(schemaTable.id);
        for (SchemaTableField schemaTableField : schemaTablesFields) {
            ArrayList<BusinessRule> businessRules = ToolDatabase.getInstance().fetchBusinessRules(schemaTableField.id);
            for (BusinessRule businessRule : businessRules) {
                if(businessRule.to_generate) {
                    BusinessRuleType businessRuleType = ToolDatabase.getInstance().fetchBusinessRuleType(businessRule.type_id);
                    BusinessContext context = new BusinessContext(businessRuleType.systemclass);
                    trigger = trigger + context.getTrigger(schemaTableField, businessRule, businessRuleType);
                }
            }
        }

        
        ArrayList<KeyValue> kvList = new ArrayList<>();
        kvList.add(new KeyValue("schemaCode",targetSchema.code));
        kvList.add(new KeyValue("tableCode",schemaTable.code));
        kvList.add(new KeyValue("tableName",schemaTable.name));
        kvList.add(new KeyValue("triggers",trigger));
        if(trigger == "") {
            return "";
        }else{
            return Context.getInstance().getTemplate("trigger", kvList);
        }
    }
}
