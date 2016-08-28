# Suyog_SauceLab_Project
Requirements:
Write some test cases against saucelabs.com using Sauce Labs. (Sign up for a free trial)
1. Clicking Pricing -> Verify indiviual plan is present and costs $49.99\n
2. Go to guinea pig page (https://saucelabs.com/test/guinea-pig) -> check first box -> and fill out email field
3. Add another test case to guineapig.feature. It should click the "i am a link"
4. "Parallelize" the framework.
i.e. the two test cases in guineapig.feature and the one test case in pricingplan.feature should run in parallel. 3 VM's on Sauce should be spun up at once. The test suite (all 3 test cases) should only take as long as the slowest / longest test case to finish.

How to execute:
This project is build using maven.
Tests can be run using "Maven test"
