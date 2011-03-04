(ns lol.algorithm-test
  (:use [clojure.test]
        [lol.algorithm]))

(deftest test-sort-by-value
  (is (= [ {:value 48} {:value 4} {:value 2} {:value 1}  ]  (sort-by-value [ {:value 4} {:value 2} {:value 48} {:value 1} ]))))

(deftest test-dimensions-of-item
  (is (= (dimensions-of-item {:id "1" :weight [5] :value 593})
         '(5))))

(deftest test-substract-from-limits
  (is (= (substract-from-limits {:weight [9]} '(4))
         '(-5)))
  (is (= (substract-from-limits {:weight [11 4]} '(20 19))
         '(9 15)))
  (is (= (substract-from-limits {:weight [5 3 12]} '(20 34 19))
         '(15 31 7))))

(deftest test-fill-knapsack
  (let [items [{:weight [642, 452]},
               {:weight [62, 152]}]
        limits [700 500]]
    (is (= [{:weight [642, 452]}] (fill-knapsack items limits))))
  (let [items [{:weight [642, 452]},
               {:weight [62, 152]}]
        limits [600 500]]
    (is (= [{:weight [62, 152]}] (fill-knapsack items limits))))
  (let [items [{:weight [642, 452]},
               {:weight [28, 152]},
               {:weight [62, 152]},
               { :weight [621, 152]}]
        limits [500 500]]
    (is (= [{:weight [62, 152]},
            {:weight [28, 152]},] (fill-knapsack items limits)))))

(deftest test-items-to-id-list
  (let [items [{:id "3"} {:id "12"} {:id "57"} {:id "1"}]]
    (is (= ["3" "12", "57" "1"] (items-to-id-list items)))))

;; (deftest test-get-magic-weight-three-dims
;;   (let [dimensions [1,8,12]
;;         limits [3 5 13]]
;;     (println "huhuu")
;;     (println get-magic-weight dimensions limits)
;;     (is (= 6 get-magic-weight dimensions limits))))
