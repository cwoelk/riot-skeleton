var RiotMultiList = Class.create({

	initialize: function(controlList) {
		this.controlList = controlList;
	},

	wire: function(target) {
		this.mainListFrame = $(target);
		this.mainListFrame.update = this.updateControlList.bind(this);
		this.mainListFrame.resize = this.resizeMainListFrame.bind(this);
		this.controlList.table.observe('list:selectionChanged',
			this.handleControlSelectionChanged.bind(this));
	},

	getMainListWindow: function() {
		return this.mainListFrame.contentWindow;
	},

  getMainList: function() {
    return this.getMainListWindow().list;
  },

	resizeMainListFrame: function(dimensions) {
    var mainListWindow = this.getMainListWindow();
    var extraHeight = mainListWindow.$('extra').getHeight(),
        iframeHeight = Math.max(dimensions.height, extraHeight);

		this.mainListFrame.setStyle({ height: iframeHeight + 'px' });
	},

	updateControlList: function(parentId, state) {
		var row = ListRow.get(parentId);
		if (row) {
			row.expand.call(row);
		}
	},

	handleControlSelectionChanged: function(ev) {
		if (ev.memo.length === 1) {
			var mainList = this.getMainList();
			ListService.gotoItemScreenUrl(this.controlList.key, ev.memo, true,
				mainList.processCommandResult.bind(mainList));
		}
	}

});
