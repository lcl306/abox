var SetTemplate = React.createClass({
	componentDidMount : function(){
		this.timer = setTimeout(function () {
	    	
	    }.bind(this), 100);
	},
	pre : function(id){
		this.refs.setPop.pre(id);
	},
	change : function(templateId, templateName){
		var self = this;
		$.get(this.props.project+"/abox/change?name="+document.getElementById(templateName).value, function(rtn){
			self.close(templateId);
		});
	},
	upload : function(templateId, templateName, templateFile){
		var self = this;
		ajaxFileUpload(this.props.project+"/abox/upload", document.getElementById(templateFile), 
			{filename:document.getElementById(templateName).value}, function(rtn){
			self.close(templateId);
		});
	},
	close : function(id){
		this.refs.setPop.close(id);
	},
	render : function(){
		return (
			<div>
				<SetPop station={this.props.station} ref="setPop" />
				<a href="#" className="mui-btn mui-btn-primary mui-btn-block mui-btn-outlined" onClick={this.pre.bind(this, "templateInfo")}>更换模板</a>
				
				<div id="templateInfo" className="mui-popover">
					<div className="templateInfo mui-input-group">
						<div className="mui-input-row mui-input-search">
        					<label>模板名</label>
    						<input type="text" id="templateName" className="mui-input-clear" />
    					</div>
    					<div>
    						<input type="file" className="mui-btn" id="templateFile" name="file" />
    					</div>
    					<p>&nbsp;</p>
						<p className="mui-text-center">
							<button type="button" className="mui-btn mui-btn-grey marginr" onClick={this.close.bind(this, 'templateInfo')} >关闭</button>
							<button type="button" className="mui-btn mui-btn-primary marginr" onClick={this.change.bind(this, 'templateInfo', 'templateName')}>更换</button>
							<button type="button" className="mui-btn mui-btn-primary" onClick={this.upload.bind(this, 'templateInfo', 'templateName', "templateFile")}>上传更换</button>
						</p>
					</div>
				</div>
				
			</div>
		);
	}
});