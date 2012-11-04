function MemoGame() {

	this.levels = 3;

	this.current_level = 1;

	this.random_obj = 0;

	this.init = function() {
		$('.cover').hide();
		$('.object').show();
		$('#random-object').hide();

		this.randomize();
	}

	this.randomize = function() {
		var list = [];
		$('.object').each(function(i,data){
			list.push(data.id);
		});
		list.sort(function() {return 0.5 - Math.random()})

		for (var i = list.length - 1; i >= 0; i--) {
			$('#'+list[i]).attr('src', this.get_url()+'/object-'+ (i+1)+'.jpg');
		};
	}

	this.get_url = function() {
		return "levels/level-"+ this.current_level;
	}

	this.start_game = function() {
		$('.cover').show();
		$('.object').hide();

		this.random_obj = Math.floor(Math.random() * 4) + 1;

		$('#random-object').attr('src', $('#object-'+this.random_obj).attr('src'));
		$('#random-object').show();
	}

	this.choice = function(obj) {
		var id = obj.currentTarget.id;
		var selected = id.substr(6,1);

		$('#'+id).hide();
		$('#object-'+selected).show();

		if (selected == this.random_obj) {
			if (this.current_level == this.levels) {
				$('#congratulations p').html('¡Perfecto!<br /> Has completado todos los niveles.');
				$('#next-button').hide();
			} else {
				$('#congratulations p').html('¡Excelente!<br /> Has superado el nivel.');
				$('#next-button').show();
			}
			

			$.fancybox($('#congratulations'));
		} else {
			setTimeout(function(){
				$('#'+id).show();
				$('#object-'+selected).hide();
			}, 500);
		}
	}

	this.change_level = function(hash) {
		this.current_level = +hash.replace('#', '');

		if (!this.current_level || this.current_level > this.levels) {
			this.current_level = 1;
			window.location.hash = 1;
		}

		this.init();
	}

	this.reset_game = function() {
		window.location.hash = 1;
		this.current_level = 1;
		this.init();
	}

	this.replay_game = function() {
		this.init();
	}

	this.next_level = function() {
		this.current_level++;
		window.location.hash = this.current_level;
		this.init();
	}
}