var Snapshot = React.createClass({
	getInitialState : function(){
		return {
			ip : "",
			template : ""
		};
	},
	// bind(this)，只能用于componentDidMount
	componentDidMount : function(){
		$.get(this.props.url, function(rtn){
			this.setState({ip:rtn.ip,template:rtn.template});
		}.bind(this));
	},
	sw : function(e){
		var sw = e.target.classList.contains("mui-active")?"true":"false";
		var imageId = this.props.imageId;
		var imageUrl = this.props.imageUrl;
		$.get(this.props.swUrl+"?switch="+sw, function(){
			document.getElementById(imageId).src = imageUrl;
		});
	},
	render : function(){
		return (
			<li className="mui-table-view-cell">
				{this.state.ip}&nbsp;&nbsp;&nbsp;{this.state.template}
				<div id="swSwitch" className="mui-switch mui-switch-blue" onClick={this.sw}>
				  <div className="mui-switch-handle"></div>
				</div>
			</li>
		);
	}
});
