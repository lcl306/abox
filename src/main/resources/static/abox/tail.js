var Tail = React.createClass({
	componentDidMount: function () {
	    this.timer = setTimeout(function () {
	    	this.refs[this.props.active].className="mui-tab-item mui-active";
	    }.bind(this), 100);
	},
	go : function(url, event){
		if(this.props.urlPre) url = this.props.urlPre+url;
		document.location.href=url;
	},
	render : function(){
		return (
			<nav className="mui-bar mui-bar-tab">
				<a id="snapshotNav" ref="snapshotNav" className="mui-tab-item" onClick={this.go.bind(this, 'snapshot.html')} href="#">
					<span className="mui-tab-label">快照</span>
				</a>
				<a id="infoNav" ref="infoNav" className="mui-tab-item" onClick={this.go.bind(this, 'info.html')} href="#">
					<span className="mui-tab-label">信息</span>
				</a>
				<a id="setNav" ref="setNav" className="mui-tab-item" onClick={this.go.bind(this, 'set.html')} href="#">
					<span className="mui-tab-label">操作</span>
				</a>
			</nav>
		)
	}
});
