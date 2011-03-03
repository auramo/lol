(ns lol.server-test
  (:use [clojure.test]
        [clojure.contrib.json]
        [lol.server]))

(defn get-testdata [filename]
   (parse-json-str (slurp (str "test/lol/" filename))))

(deftest test-sort-by-value
  (is (= [ {:value 1} {:value 2} {:value 4} {:value 48}  ]  (sort-by-value [ {:value 4} {:value 2} {:value 48} {:value 1} ]))))

(deftest test-dimensions-of-item
  (is (= (dimensions-of-item {:id "1" :weight [5] :value 593})
         '(5))))

(deftest test-substract-from-limits
  (is (= (substract-from-limits '(4) {:weight [9]})
         '(-5)))
  (is (= (substract-from-limits '(20 19) {:weight [11 4]})
         '(9 15)))
  (is (= (substract-from-limits '(20 34 19) {:weight [5 3 12]})
         '(15 31 7))))

(deftest test-parse-json-str
  (is (= {:name "test" :timeout 15000 :contents [{:id "1" :weight [642, 452] :value 593}] :capacity [2000 2000 2000]}
    (get-testdata "parse_test.json"))))

