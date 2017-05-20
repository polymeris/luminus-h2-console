(ns luminus.h2-console
  (:require [clojure.tools.logging :as log])
  (:import [org.h2.tools Server]))


(defn start
  "Start an H2 web console on specified port"
  [{:keys [port]}]
  (try
    (log/info "starting H2 web console on port" port)
    (let [server (Server/createWebServer (into-array ["-web" "-webPort" (str port)]))]
      (.start server)
      server)
    (catch Throwable t
      (log/error t "failed to start H2 web console")
      (throw t))))

(defn stop [server]
  (when (and server (.isRunning server false))
    (.stop server)
    (log/info "H2 web console stopped")))
