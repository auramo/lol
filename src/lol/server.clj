(ns lol.server
  (:use
   [ring.adapter.jetty :only [run-jetty]]
   [clojure.contrib.json]
   [clojure.contrib.duck-streams])
  (:gen-class))

(defn input-as-str [req]
  (slurp* (reader (:body req))))

(defn app [req]
  (println (input-as-str req))
  {:status  200
   :headers {"Content-Type" "application/json"}
   :body    "meh"})

(defn -main
  []
  (run-jetty app {:port 8080}))
