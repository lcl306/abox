var items = [{value:'-1',text:'选择'}, 
			{value:'1',text:'visible'},{value:'2',text:'disable'},
			{value:'3',text:'bkcolor'}, {value:'4',text:'color'}, 
			{value:'5',text:'text'}, {value:'6',text:'blink'}, 
			{value:'7',text:'barcolor'}, {value:'8',text:'percentage'},
			{value:'9',text:'imagefile'}, {value:'10',text:'action'}];

var SetSet = React.createClass({
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
	set : function(setId, setName, setAttr, setVal){
		var info ={name: document.getElementById(setName).value, attr: document.getElementById(setAttr).value, val:document.getElementById(setVal).value};
		var self = this;
		$.post(this.props.project+"/abox/set", info, function(rtn){
			self.close(setId);
		});
	},
	render : function(){
		return (
			<div>
				<SetPop ref="setPop" station={this.props.station} />
				<a href="#" className="mui-btn mui-btn-primary mui-btn-block mui-btn-outlined" onClick={this.pre.bind(this, "setInfo")}>设值</a>
				
				<div id="setInfo" className="mui-popover">
					<div className="setInfo mui-input-group">
    					<div className="mui-input-row mui-input-search">
        					<label>组件名</label>
    						<input type="text" id="setName" className="mui-input-clear" />
    					</div>
    					<div className="mui-input-row">
        					<label>组件属性</label>
        					<select id="setAttr">{
        						items.map(function(item){
        							return <option value={item.text}>{item.text}</option>
        						})
        					}</select>
    					</div>
						<div className="mui-input-row mui-input-search">
        					<label>组件值</label>
    						<input type="text" id="setVal" className="mui-input-clear" />
    					</div>
    					<p>&nbsp;</p>
						<p className="mui-text-center">
							<button type="button" className="mui-btn mui-btn-grey marginr" onClick={this.close.bind(this, 'setInfo')} >关闭</button>
							<button type="button" className="mui-btn mui-btn-primary" 
							 onClick={this.set.bind(this, 'setInfo', 'setName', 'setAttr', 'setVal')}>设置</button>
						</p>
					</div>
				</div>
				
			</div>
		);
	}
});