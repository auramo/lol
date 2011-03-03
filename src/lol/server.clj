(ns lol.server
  (:use
   [ring.adapter.jetty :only [run-jetty]]
   [clojure.contrib.json]
   [clojure.contrib.duck-streams])
  (:gen-class))

(defn app [req]
  (println (slurp* (reader (:body req))))
;  (read-json (:body req))
  {:status  200
   :headers {"Content-Type" "application/json"}
   :body    "meh"})

(defn -main
  []
  (run-jetty app {:port 8080}))
