package org.apache.ranger.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ranger.common.AppConstants;
import org.apache.ranger.common.GUIDUtil;
import org.apache.ranger.common.MessageEnums;
import org.apache.ranger.entity.XXAccessTypeDef;
import org.apache.ranger.entity.XXContextEnricherDef;
import org.apache.ranger.entity.XXDBBase;
import org.apache.ranger.entity.XXEnumDef;
import org.apache.ranger.entity.XXEnumElementDef;
import org.apache.ranger.entity.XXPolicyConditionDef;
import org.apache.ranger.entity.XXResourceDef;
import org.apache.ranger.entity.XXServiceConfigDef;
import org.apache.ranger.entity.XXServiceDef;
import org.apache.ranger.plugin.model.RangerServiceDef;
import org.apache.ranger.plugin.model.RangerServiceDef.RangerAccessTypeDef;
import org.apache.ranger.plugin.model.RangerServiceDef.RangerContextEnricherDef;
import org.apache.ranger.plugin.model.RangerServiceDef.RangerEnumDef;
import org.apache.ranger.plugin.model.RangerServiceDef.RangerEnumElementDef;
import org.apache.ranger.plugin.model.RangerServiceDef.RangerPolicyConditionDef;
import org.apache.ranger.plugin.model.RangerServiceDef.RangerResourceDef;
import org.apache.ranger.plugin.model.RangerServiceDef.RangerServiceConfigDef;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class RangerServiceDefServiceBase<T extends XXServiceDef, V extends RangerServiceDef>
		extends RangerBaseModelService<T, V> {
	private static final Log LOG = LogFactory.getLog(RangerServiceDefServiceBase.class);

	@Autowired
	RangerAuditFields<XXDBBase> rangerAuditFields;
	
	@SuppressWarnings("unchecked")
	@Override
	protected XXServiceDef mapViewToEntityBean(RangerServiceDef vObj, XXServiceDef xObj, int operationContext) {
		
		String guid = (StringUtils.isEmpty(vObj.getGuid())) ? GUIDUtil.genGUI() : vObj.getGuid();
		
		xObj.setGuid(guid);
		xObj.setVersion(vObj.getVersion());
		xObj.setName(vObj.getName());
		xObj.setImplclassname(vObj.getImplClass());
		xObj.setLabel(vObj.getLabel());
		xObj.setDescription(vObj.getDescription());
		xObj.setRbkeylabel(vObj.getRbKeyLabel());
		xObj.setRbkeydescription(vObj.getRbKeyDescription());
		xObj.setIsEnabled(vObj.getIsEnabled());
		return xObj;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected RangerServiceDef mapEntityToViewBean(RangerServiceDef vObj, XXServiceDef xObj) {
		vObj.setGuid(xObj.getGuid());
		vObj.setVersion(xObj.getVersion());
		vObj.setName(xObj.getName());
		vObj.setImplClass(xObj.getImplclassname());
		vObj.setLabel(xObj.getLabel());
		vObj.setDescription(xObj.getDescription());
		vObj.setRbKeyLabel(xObj.getRbkeylabel());
		vObj.setRbKeyDescription(xObj.getRbkeydescription());
		vObj.setIsEnabled(xObj.getIsEnabled());
		return vObj;
	}
	
	public XXServiceConfigDef populateRangerServiceConfigDefToXX(RangerServiceConfigDef vObj, XXServiceConfigDef xObj, XXServiceDef serviceDef) {
		if(serviceDef == null) {
			LOG.error("RangerServiceDefServiceBase.populateRangerServiceConfigDefToXX, serviceDef can not be null");
			throw restErrorUtil.createRESTException("RangerServiceDef cannot be null.", MessageEnums.DATA_NOT_FOUND);
		}
		
		xObj = (XXServiceConfigDef) rangerAuditFields.populateAuditFields(xObj, serviceDef);
		xObj.setDefid(serviceDef.getId());
		xObj.setName(vObj.getName());
		xObj.setType(vObj.getType());
		xObj.setSubtype(vObj.getSubType());
		xObj.setIsMandatory(vObj.getMandatory());
		xObj.setDefaultvalue(vObj.getDefaultValue());
		xObj.setValidationRegEx(vObj.getValidationRegEx());
		xObj.setValidationMessage(vObj.getValidationMessage());
		xObj.setUiHint(vObj.getUiHint());
		xObj.setLabel(vObj.getLabel());
		xObj.setDescription(vObj.getDescription());
		xObj.setRbkeylabel(vObj.getRbKeyLabel());
		xObj.setRbkeydescription(vObj.getRbKeyDescription());
		xObj.setRbKeyValidationMessage(vObj.getRbKeyValidationMessage());
		xObj.setOrder(AppConstants.DEFAULT_SORT_ORDER);
		return xObj;
	}

	public RangerServiceConfigDef populateXXToRangerServiceConfigDef(XXServiceConfigDef xObj) {
		RangerServiceConfigDef vObj = new RangerServiceConfigDef();
		vObj.setName(xObj.getName());
		vObj.setType(xObj.getType());
		vObj.setSubType(xObj.getSubtype());
		vObj.setMandatory(xObj.getIsMandatory());
		vObj.setDefaultValue(xObj.getDefaultvalue());
		vObj.setValidationRegEx(xObj.getValidationRegEx());
		vObj.setValidationMessage(xObj.getValidationMessage());
		vObj.setUiHint(xObj.getUiHint());
		vObj.setLabel(xObj.getLabel());
		vObj.setDescription(xObj.getDescription());
		vObj.setRbKeyLabel(xObj.getRbkeylabel());
		vObj.setRbKeyDescription(xObj.getRbkeydescription());
		vObj.setRbKeyValidationMessage(xObj.getRbKeyValidationMessage());
		return vObj;
	}
	
	public XXResourceDef populateRangerResourceDefToXX(RangerResourceDef vObj, XXResourceDef xObj, XXServiceDef serviceDef) {
		if(serviceDef == null) {
			LOG.error("RangerServiceDefServiceBase.populateRangerResourceDefToXX, serviceDef can not be null");
			throw restErrorUtil.createRESTException("RangerServiceDef cannot be null.", MessageEnums.DATA_NOT_FOUND);
		}
		xObj = (XXResourceDef) rangerAuditFields.populateAuditFields(xObj, serviceDef);
		xObj.setDefid(serviceDef.getId());
		xObj.setName(vObj.getName());
		xObj.setType(vObj.getType());
		xObj.setLevel(vObj.getLevel());
		xObj.setMandatory(vObj.getMandatory());
		xObj.setLookupsupported(vObj.getLookupSupported());
		xObj.setRecursivesupported(vObj.getRecursiveSupported());
		xObj.setExcludessupported(vObj.getExcludesSupported());
		xObj.setMatcher(vObj.getMatcher());
		xObj.setMatcheroptions(vObj.getMatcherOptions());
		xObj.setValidationRegEx(vObj.getValidationRegEx());
		xObj.setValidationMessage(vObj.getValidationMessage());
		xObj.setUiHint(vObj.getUiHint());
		xObj.setLabel(vObj.getLabel());
		xObj.setDescription(vObj.getDescription());
		xObj.setRbkeylabel(vObj.getRbKeyLabel());
		xObj.setRbkeydescription(vObj.getRbKeyDescription());
		xObj.setRbKeyValidationMessage(vObj.getRbKeyValidationMessage());
		xObj.setOrder(AppConstants.DEFAULT_SORT_ORDER);
		return xObj;
	}
	
	public RangerResourceDef populateXXToRangerResourceDef(XXResourceDef xObj) {
		RangerResourceDef vObj = new RangerResourceDef();
		vObj.setName(xObj.getName());
		vObj.setType(xObj.getType());
		vObj.setLevel(xObj.getLevel());		
		vObj.setMandatory(xObj.getMandatory());
		vObj.setLookupSupported(xObj.getLookupsupported());
		vObj.setRecursiveSupported(xObj.getRecursivesupported());
		vObj.setExcludesSupported(xObj.getExcludessupported());
		vObj.setMatcher(xObj.getMatcher());
		vObj.setMatcherOptions(xObj.getMatcheroptions());
		vObj.setValidationRegEx(xObj.getValidationRegEx());
		vObj.setValidationMessage(xObj.getValidationMessage());
		vObj.setUiHint(xObj.getUiHint());
		vObj.setLabel(xObj.getLabel());
		vObj.setDescription(xObj.getDescription());
		vObj.setRbKeyLabel(xObj.getRbkeylabel());
		vObj.setRbKeyDescription(xObj.getRbkeydescription());
		vObj.setRbKeyValidationMessage(xObj.getRbKeyValidationMessage());
		
		XXResourceDef parent = daoMgr.getXXResourceDef().getById(xObj.getParent());
		String parentName = (parent != null) ? parent.getName() : null;
		vObj.setParent(parentName);
		
		return vObj;
	}
	
	public XXAccessTypeDef populateRangerAccessTypeDefToXX(RangerAccessTypeDef vObj, XXAccessTypeDef xObj, XXServiceDef serviceDef) {
		if(serviceDef == null) {
			LOG.error("RangerServiceDefServiceBase.populateRangerAccessTypeDefToXX, serviceDef can not be null");
			throw restErrorUtil.createRESTException("RangerServiceDef cannot be null.", MessageEnums.DATA_NOT_FOUND);
		}
		xObj = (XXAccessTypeDef) rangerAuditFields.populateAuditFields(xObj, serviceDef);
		xObj.setDefid(serviceDef.getId());
		xObj.setName(vObj.getName());
		xObj.setLabel(vObj.getLabel());
		xObj.setRbkeylabel(vObj.getRbKeyLabel());
		xObj.setOrder(AppConstants.DEFAULT_SORT_ORDER);
		return xObj;
	}
	
	public RangerAccessTypeDef populateXXToRangerAccessTypeDef(XXAccessTypeDef xObj) {
		RangerAccessTypeDef vObj = new RangerAccessTypeDef();
		vObj.setName(xObj.getName());
		vObj.setLabel(xObj.getLabel());
		vObj.setRbKeyLabel(xObj.getRbkeylabel());
		
		List<String> impliedGrants = daoMgr.getXXAccessTypeDefGrants().findImpliedGrantsByATDId(xObj.getId());
		vObj.setImpliedGrants(impliedGrants);
		return vObj;
	}
	
	public XXPolicyConditionDef populateRangerPolicyConditionDefToXX(RangerPolicyConditionDef vObj, XXPolicyConditionDef xObj, XXServiceDef serviceDef) {
		if(serviceDef == null) {
			LOG.error("RangerServiceDefServiceBase.populateRangerPolicyConditionDefToXX, serviceDef can not be null");
			throw restErrorUtil.createRESTException("RangerServiceDef cannot be null.", MessageEnums.DATA_NOT_FOUND);
		}
		xObj = (XXPolicyConditionDef) rangerAuditFields.populateAuditFields(xObj, serviceDef);
		xObj.setDefid(serviceDef.getId());
		xObj.setName(vObj.getName());
		xObj.setEvaluator(vObj.getEvaluator());
		xObj.setEvaluatoroptions(vObj.getEvaluatorOptions());
		xObj.setValidationRegEx(vObj.getValidationRegEx());
		xObj.setValidationMessage(vObj.getValidationMessage());
		xObj.setUiHint(vObj.getUiHint());
		xObj.setLabel(vObj.getLabel());
		xObj.setDescription(vObj.getDescription());
		xObj.setRbkeylabel(vObj.getRbKeyLabel());
		xObj.setRbkeydescription(vObj.getRbKeyDescription());
		xObj.setRbKeyValidationMessage(vObj.getRbKeyValidationMessage());
		xObj.setOrder(AppConstants.DEFAULT_SORT_ORDER);
		return xObj;
	}
	
	public RangerPolicyConditionDef populateXXToRangerPolicyConditionDef(XXPolicyConditionDef xObj) {
		RangerPolicyConditionDef vObj = new RangerPolicyConditionDef();
		vObj.setName(xObj.getName());
		vObj.setEvaluator(xObj.getEvaluator());
		vObj.setEvaluatorOptions(xObj.getEvaluatoroptions());
		vObj.setValidationRegEx(xObj.getValidationRegEx());
		vObj.setValidationMessage(xObj.getValidationMessage());
		vObj.setUiHint(xObj.getUiHint());
		vObj.setLabel(xObj.getLabel());
		vObj.setDescription(xObj.getDescription());
		vObj.setRbKeyLabel(xObj.getRbkeylabel());
		vObj.setRbKeyDescription(xObj.getRbkeydescription());
		vObj.setRbKeyValidationMessage(xObj.getRbKeyValidationMessage());
		return vObj;
	}
	
	public XXContextEnricherDef populateRangerContextEnricherDefToXX(RangerContextEnricherDef vObj, XXContextEnricherDef xObj, XXServiceDef serviceDef) {
		if(serviceDef == null) {
			LOG.error("RangerServiceDefServiceBase.populateRangerContextEnricherDefToXX, serviceDef can not be null");
			throw restErrorUtil.createRESTException("RangerServiceDef cannot be null.", MessageEnums.DATA_NOT_FOUND);
		}
		xObj = (XXContextEnricherDef) rangerAuditFields.populateAuditFields(xObj, serviceDef);
		xObj.setDefid(serviceDef.getId());
		xObj.setName(vObj.getName());
		xObj.setEnricher(vObj.getEnricher());
		xObj.setEnricherOptions(vObj.getEnricherOptions());
		xObj.setOrder(AppConstants.DEFAULT_SORT_ORDER);
		return xObj;
	}
	
	public RangerContextEnricherDef populateXXToRangerContextEnricherDef(XXContextEnricherDef xObj) {
		RangerContextEnricherDef vObj = new RangerContextEnricherDef();
		vObj.setName(xObj.getName());
		vObj.setEnricher(xObj.getEnricher());
		vObj.setEnricherOptions(xObj.getEnricherOptions());
		return vObj;
	}
	
	public XXEnumDef populateRangerEnumDefToXX(RangerEnumDef vObj, XXEnumDef xObj, XXServiceDef serviceDef) {
		if(serviceDef == null) {
			LOG.error("RangerServiceDefServiceBase.populateRangerEnumDefToXX, serviceDef can not be null");
			throw restErrorUtil.createRESTException("RangerServiceDef cannot be null.", MessageEnums.DATA_NOT_FOUND);
		}
		xObj = (XXEnumDef) rangerAuditFields.populateAuditFields(xObj, serviceDef);
		xObj.setDefid(serviceDef.getId());
		xObj.setName(vObj.getName());
		xObj.setDefaultindex(vObj.getDefaultIndex());
		return xObj;
	}
	
	public RangerEnumDef populateXXToRangerEnumDef(XXEnumDef xObj) {
		RangerEnumDef vObj = new RangerEnumDef();
		vObj.setName(xObj.getName());
		vObj.setDefaultIndex(xObj.getDefaultindex());
		
		List<XXEnumElementDef> xElements = daoMgr.getXXEnumElementDef().findByEnumDefId(xObj.getId());
		List<RangerEnumElementDef> elements = new ArrayList<RangerEnumElementDef>();
		
		for(XXEnumElementDef xEle : xElements) {
			RangerEnumElementDef element = populateXXToRangerEnumElementDef(xEle);
			elements.add(element);
		}
		vObj.setElements(elements);
		
		return vObj;
	}
	
	public XXEnumElementDef populateRangerEnumElementDefToXX(RangerEnumElementDef vObj, XXEnumElementDef xObj, XXEnumDef enumDef) {
		if(enumDef == null) {
			LOG.error("RangerServiceDefServiceBase.populateRangerEnumElementDefToXX, enumDef can not be null");
			throw restErrorUtil.createRESTException("enumDef cannot be null.", MessageEnums.DATA_NOT_FOUND);
		}
		xObj = (XXEnumElementDef) rangerAuditFields.populateAuditFields(xObj, enumDef);
		xObj.setEnumdefid(enumDef.getId());
		xObj.setName(vObj.getName());
		xObj.setLabel(vObj.getLabel());
		xObj.setRbkeylabel(vObj.getRbKeyLabel());
		xObj.setOrder(AppConstants.DEFAULT_SORT_ORDER);
		return xObj;
	}
	
	public RangerEnumElementDef populateXXToRangerEnumElementDef(XXEnumElementDef xObj) {
		RangerEnumElementDef vObj = new RangerEnumElementDef();
		vObj.setName(xObj.getName());
		vObj.setLabel(xObj.getLabel());
		vObj.setRbKeyLabel(xObj.getRbkeylabel());
		return vObj;
	}
	
}
