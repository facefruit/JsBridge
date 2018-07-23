window.jsBridge = {
    run : function(host, path, arg, id) {
        location.href = "jsBridge://" + host + "/" + path + "?params=" + JSON.stringify(arg) + "&id=" + id;
    },

    doJs : function(host, path, arg) {
        var id = window.callback.add(function(rtn, err) {
            if (rtn.status == 0) {
                arg.cancel(rtn);
            } else if (rtn.status == -1) {
                arg.error(rtn, err);
            } else if (rtn.status == 1) {
                arg.success(rtn);
            }
        })
        this.run(host, path, arg, id);
    }
}

window.callback = {
    list : {},
    id : 1,
    add : function(arg) {
        this.list[this.id] = arg;
        return this.id++;
    },
    cb : function(id, rtn, err, del) {
        this.list[id] && this.list[id](rtn, err);
        if (del) {
            delete this.list[id];
        }
    }
}
