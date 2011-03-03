(ns lol.server-test
  (:use [clojure.test]
        [clojure.contrib.json]
        [lol.server]))

(defn get-testdata [filename]
   (read-json (slurp (str "test/lol/" filename))))

(deftest test-sort-by-value
  (is (= [ {:value 1} {:value 2} {:value 4} {:value 48}  ]  (sort-by-value [ {:value 4} {:value 2} {:value 48} {:value 1} ]))))

(deftest test-fill-knapsack
  (do-the-real-shit (get-testdata "dataset1.json")))

(deftest test-substract-from-limits
  (is (= (substract-from-limits '(4) '(9))
         '(-5)))
  (is (= (substract-from-limits '(20 19) '(11 4))
         '(9 15)))
  (is (= (substract-from-limits '(20 34 19) '(5 3 12))
         '(15 31 7))))