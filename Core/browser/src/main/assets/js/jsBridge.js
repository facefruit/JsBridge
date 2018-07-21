window.jsBridge = {
    run : function(host, path, arg) {
        location.href = "jsBridge://" + host + "/" + path + "?params=" + JSON.stringify(arg)
    }
}

window.api = {
    log : {
        d : function(arg) {
            jsBridge.run('log', 'd', arg)
        },
        i : function(arg) {
            jsBridge.run('log', 'i', arg)
        }
    }
}