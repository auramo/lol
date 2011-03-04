(ns lol.algorithm-test
  (:use [clojure.test]
        [lol.algorithm]))

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

(deftest test-fill-knapsack
  (let [items [{:id "1" :weight [642, 452] :value 593},{:id "2" :weight [62, 152] :value 93}]
        limits [700 500]]
    (is (= [{:id "1" :weight [642, 452] :value 593}] (fill-knapsack items limits)))))

(deftest test-items-to-id-list
  (let [items [{:id "3"} {:id "12"} {:id "57"} {:id "1"}]]
    (is (= ["3" "12", "57" "1"] (items-to-id-list items)))))