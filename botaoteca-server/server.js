var http = require("http");
var fs = require("fs");

http.createServer(function(req,res){
	if(req.url == "/list"){
		list(res);
	}
	else if(req.url.substr(0, "/download".length) == "/download") {
		var info = req.url.split("/");
		download(res,info[2]);
	}
}).listen(9080);

function list(res) {
	fs.readdir('sounds', function (err, data) {
	  	if (err) throw err;	  	
	  	var sounds_array = new Array();
	  	for(i = 0; i < data.length; i++) {
	  		var name = data[i].split(".")[0];
	  		sounds_array.push({name: name});
	  	}
	  	sounds_array.sort(function(a,b){
	  	  if (a.name == b.name) return 0;
	  	  return a.name > b.name ? 1 : -1;
	  	});
	  	res.writeHead(200,{'Content-type': 'application/json',charset: 'utf-8'});
	  	res.end(JSON.stringify({sounds: sounds_array}));
	});
}

function download(res, filename) {
	filename = decodeURI(filename);
	fs.readFile("sounds/"+filename, function(err, data) {
		if(err) throw err;
		res.writeHead(200,{'Content-type': 'audio/mp3','Content-Length' : data.length});
		res.end(data);
	});	
}

