var SetOther = React.createClass({
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
	reload : function(id){
		var self = this;
		$.get(this.props.project+"/abox/reload", function(rtn){
			self.close(id);
			document.location.href="snapshot.html";
		});
	},
	reboot : function(id){
		var self = this;
		$.get(this.props.project+"/abox/reboot", function(rtn){
			self.close(id);
			document.location.href="snapshot.html";
		});
	},
	render : function(){
		return (
			<div>
				<SetPop ref="setPop" station={this.props.station} />
				<a href="#" className="mui-btn mui-btn-primary mui-btn-block mui-btn-outlined" onClick={this.pre.bind(this, "otherInfo")}>其它</a>
				
				<div id="otherInfo" className="mui-popover">
					<div className="otherInfo">
						<p>&nbsp;</p>
						<p className="mui-text-center">
							<button type="button" className="mui-btn mui-btn-grey marginr" onClick={this.close.bind(this, 'otherInfo')} >关闭</button>
							<button type="button" className="mui-btn mui-btn-primary marginr" onClick={this.reload.bind(this, 'otherInfo')} >reload</button>
							<button type="button" className="mui-btn mui-btn-primary" onClick={this.reboot.bind(this, 'otherInfo')}>reboot</button>
						</p>
					</div>
				</div>
				
			</div>
		);
	}
});