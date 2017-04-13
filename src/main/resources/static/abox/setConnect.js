var SetConnect = React.createClass({
	componentDidMount : function(){
		this.timer = setTimeout(function () {
	    	
	    }.bind(this), 100);
	},
	pre : function(id){
		this.refs.setPop.pre(id);
	},
	close : function(id){
		this.refs.setPop.close(id);
	},
	connect : function(connectId, ipName, portName){
		var info = {ip: document.getElementById(ipName).value, port : document.getElementById(portName).value};
		var self = this;
		$.post(this.props.project+"/abox/connect", info, function(rtn){
			self.close(connectId);
		});
	},
	render : function(){
		return (
			<div>
				<SetPop station={this.props.station} ref="setPop" />
				<a href="#" className="mui-btn mui-btn-primary mui-btn-block mui-btn-outlined" onClick={this.pre.bind(this, "connectInfo")}>更换连接</a>
				
				<div id="connectInfo" className="mui-popover">
					<div className="connectInfo mui-input-group">
						<div className="mui-input-row mui-input-search">
        					<label>IP</label>
    						<input type="text" id="ipName" className="mui-input-clear" />
    					</div>
    					<div className="mui-input-row mui-input-search">
        					<label>PORT</label>
    						<input type="text" id="portName" className="mui-input-clear" placeholder="8080" />
    					</div>	
    					<p>&nbsp;</p>
						<p className="mui-text-center">
							<button type="button" className="mui-btn mui-btn-grey marginr" onClick={this.close.bind(this, 'connectInfo')} >关闭</button>
							<button type="button" className="mui-btn mui-btn-primary" 
							 onClick={this.connect.bind(this, 'connectInfo', 'ipName', 'portName')}>更换连接</button>
						</p>
					</div>
				</div>
				
			</div>
		);
	}
});