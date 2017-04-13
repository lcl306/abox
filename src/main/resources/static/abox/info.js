var Info = React.createClass({
	componentDidMount : function(){
		$.get(this.props.url, function(rtn){
			if(this.isMounted()){
				$("#"+this.props.content).html(rtn);
			}
		}.bind(this));
	},
	render : function(){
		return (
			<div></div>
		);
	}
});
