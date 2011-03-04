(ns lol.server-test
  (:use [clojure.test]
        [lol.server]))

(defn get-testdata [filename]
   (parse-json-str (slurp (str "test/lol/" filename))))


(deftest test-parse-json-str
  (is (= {:name "test" :timeout 15000 :contents [{:id "1" :weight [642, 452] :value 593},{:id "2" :weight [62, 152] :value 93}] :capacity [2000 2000 2000]}
    (get-testdata "parse_test.json"))))

