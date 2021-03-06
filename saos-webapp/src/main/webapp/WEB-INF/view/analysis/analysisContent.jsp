<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglibs.jsp" %>


	
<div>
    <h1 class="content-header" ><spring:message code="analysis.chart.header" /></h1>

    <div class="form-inline yaxisValueSelect">
        <label class="control-label"><spring:message code="analysis.ysettings.yaxis.label"/></label>
        <select id="yaxisValueType" name="ysettings.valueType" class="form-control">
             <saos:enumOptions enumType="pl.edu.icm.saos.webapp.analysis.request.UiySettings.UiyValueType" selected="${analysisForm.ysettings.valueType}"/>
        </select>
    </div>
                
</div>


	<a id="exportToCsv-MAIN_CHART" class="export-csv" style="float:right" href="" data-toggle="tooltip" data-placement="left" title="<spring:message code='analysis.button.exportToCsv'/>">
	    <img style="cursor: pointer;"  src="${contextPath}/static/image/icons/exportCsv.png" alt="<spring:message code='analysis.button.exportMainChartToCsv.aria'/>"/>
	</a>


<div class="clearfix"></div>

<div aria-label="<spring:message code='analysis.mainChart.title'/>">

	<div class="panel panel-default" style="border-width: 2px;" aria-hidden="true">
	
	    <div class="panel-body">
	    
	
			<div class="col-xs-12 col-sm-9">
			    <span class="small" id="mainChartZoomCancelHint"></span>
			    <div id="mainChart" style="width: 100%; height: 300px;">
			    </div>
			</div>
		   
			<div class="col-xs-5 col-sm-3">
			    <span class="small" id="aggregatedMainChartZoomCancelHint"></span>
			    <div id="aggregatedMainChart" style="width: 100%; height: 180px;">
			    </div>
			</div>
	
	   </div>
	
	</div>
	
</div>
   
<table id="mainChartTable" class="table visuallyhidden" tabindex="0">
    <caption><spring:message code='analysis.mainChart.table.caption'/></caption>
    <thead></thead>
    <tbody></tbody>
</table>     
  
<div id="ccCourtChartDiv" style="margin-top: 40px;">

	<div>
	    <a id="exportToCsv-CC_COURT_CHART" class="export-csv" style="float:right" href="" data-toggle="tooltip" data-placement="left" title="<spring:message code='analysis.button.exportToCsv'/>">
	        <img style="cursor: pointer;"  src="${contextPath}/static/image/icons/exportCsv.png" alt="<spring:message code='analysis.button.exportCcCourtChartToCsv.aria'/>"/>
	    </a>
	 </div>
	 
	 <div class="clearfix"></div>
	
	 <div aria-label="<spring:message code='analysis.ccCourtChart.title'/>">
	
		 <div id="ccCourtChartPanel" class="panel panel-default" style="border-width: 2px; " aria-hidden="true">
		    <div class="panel-body">
		 
		  <div class="col-xs-12 col-sm-12">
		      <span class="small" id="ccCourtChartZoomCancelHint"></span>
		      <div id="ccCourtChart" style="width: 100%; height: 300px;">
		      </div>
		   </div>
		 
		     </div>
		</div>
	</div>
	
	<table id="ccCourtChartTable" class="table visuallyhidden" tabindex="0">
        <caption><spring:message code='analysis.ccCourtChart.table.caption'/></caption>
        <thead></thead>
        <tbody></tbody>
    </table>  

</div>





<img id="ajaxChartLoaderImg" src="${contextPath}/static/image/icons/ajax-loader.gif" alt="<spring:message code='analysis.button.ajaxLoader.iconAlt'/>" style="display:none;"/>
<div id="simpleChartPointTooltip" class="chart-point-tooltip" style="display:none;"></div>

<div id="mainChartPointTooltip" class="chart-point-tooltip" style="display:none" >
    <div>
        <span><spring:message code="analysis.chart.tooltip.timePeriod"/>: </span><b><span id="pointTimePeriod_mainChart"></span></b>
    </div>
    <div>
        <span><spring:message code="analysis.chart.tooltip.searchedPhrase"/>: </span><b><span id="pointSearchedPhrase_mainChart"></span></b>
    </div>
    <div>
        <span><spring:message code="analysis.chart.tooltip.judgmentCount"/>: </span><b><span id="pointJudgmentCount_mainChart"></span></b>
    </div>
    <div id="pointSearchLinkDiv_mainChart" class="point-search-link">    
        <a id="pointSearchLink_mainChart" href=""><spring:message code="analysis.chart.tooltip.searchLink"/></a>
    </div>
</div>

<div id="aggregatedMainChartPointTooltip" class="chart-point-tooltip" style="display:none; width: 230px;">
    <div>
        <span><spring:message code="analysis.chart.tooltip.timePeriod"/>: </span><b><span id="pointTimePeriod_aggregatedMainChart"></span></b>
    </div>
    <div>
        <span><spring:message code="analysis.chart.tooltip.searchedPhrase"/>: </span><b><span id="pointSearchedPhrase_aggregatedMainChart"></span></b>
    </div>
    <div>
        <span><spring:message code="analysis.chart.tooltip.judgmentCount"/>: </span><b><span id="pointJudgmentCount_aggregatedMainChart"></span></b>
    </div>
    <div id="pointSearchLinkDiv_aggregatedMainChart" class="point-search-link">    
        <a id="pointSearchLink_aggregatedMainChart" href=""><spring:message code="analysis.chart.tooltip.searchLink"/></a>
    </div>
</div>

<div id="ccCourtChartPointTooltip" class="chart-point-tooltip" style="display:none">
    <div>
        <b><span id="pointCourtName"></span></b>
    </div>
    <div>
        <span><spring:message code="analysis.chart.tooltip.timePeriod"/>: </span><b><span id="pointTimePeriod_ccCourtChart"></span></b>
    </div>
    
    <div>
        <span><spring:message code="analysis.chart.tooltip.searchedPhrase"/>: </span><b><span id="pointSearchedPhrase_ccCourtChart"></span></b>
    </div>
    
    <div>
        <span><spring:message code="analysis.chart.tooltip.judgmentCount"/>: </span><b><span id="pointJudgmentCount_ccCourtChart"></span></b>
    </div>
    <div id="pointSearchLinkDiv_ccCourtChart" class="point-search-link">    
        <a id="pointSearchLink_ccCourtChart" href=""><spring:message code="analysis.chart.tooltip.searchLink"/></a>
    </div>
</div>
