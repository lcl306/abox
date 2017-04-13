var SetPop = React.createClass({
	componentDidMount : function(){
		this.timer = setTimeout(function () {
	    	
	    }.bind(this), 100);
	},
	pre : function(id){
		mui('.mui-popover').popover("hide", document.getElementById(this.props.station));
		mui('#'+id).popover("show", document.getElementById(this.props.station));
	},
	close : function(id){
		mui('#'+id).popover("hide", document.getElementById(this.props.station));
	},
	render : function(){
		return (
			<span></span>
		);
	}
});