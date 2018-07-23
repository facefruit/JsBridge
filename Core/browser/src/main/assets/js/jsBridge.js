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
    },

    doJsNcb : function (host, path, arg, id) {
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

window.nativeJs = {
    callback : function(id, rtn, status) {
        if (status == 0) {
            jsBridge.doJsNcb("nativejs", "cancel", rtn, id)
        } else if (status == -1) {
            jsBridge.doJsNcb("nativejs", "error", rtn, id)
        } else if (status == 1) {
            jsBridge.doJsNcb("nativejs", "success", rtn, id)
        }
    }
}

