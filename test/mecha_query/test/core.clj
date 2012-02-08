(ns mecha-query.test.core
  (:use [mecha-query.core])
  (:use [clojure.test]))

(deftest create-process
         (let [p (process :cat "mittens" ["food" {:meow "treat"}])]
           (is (= (.fun p) "cat"))
           (is (= (.name p) "mittens"))
           (is (= (.args p) ["food" {:meow "treat"}]))
           (is (= (.children p) []))
           (is (= (.children p) []))))

(deftest pipe-single
         (let [a (process :cat :spook)
               b (process :cat :peaches)]
           (pipe a b)
           (is (= (.children a) [b]))
           (is (= (.parents b) [a]))))

(deftest pipe-vec
         (let [c1 (process :cat :spook)
               c2 (process :cat :peaches)
               m1 (process :mouse :ralph)
               m2 (process :mouse :frisby)]

           (| [m1 m2] [c1 c2])
           (is (= (.children m1) [c1 c2]))
           (is (= (.children m2) [c1 c2]))
           (is (= (.parents  c1) [m1 m2]))
           (is (= (.parents  c2) [m1 m2]))))

(deftest get-test
         (let [g1 (get "bucket" "key")
               g2 (get "bucket" "key" {:timeout 5})]
           (is (= (.args g1) ["bucket" "key" {}]))
           (is (= (.args g2) ["bucket" "key" {:timeout 5}]))))

(deftest warp-test
         (let [w (warp :do (get "buck" "k"))
               sub (:do (first (.args w)))]

           (is (= (.args sub) ["buck" "k" {}]))))
