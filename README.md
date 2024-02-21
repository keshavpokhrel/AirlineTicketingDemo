# Prerequsites

Local environment should have nodejs, playwright, VSCode or any IDE to work on current project.

# Playwright
This repository contains a Proof Of Concept of the `Playwright` test automation framework for Web apps on multiple Web browsers: Chrome, Firefox, WebKit (Safari) etc.

## Running with Playwright

To run test cases of specific browser : 

**Firefox**
```
npx playwright test -g 'Publish Art' --project=firefox

```
**Chrome**
```
npx playwright test -g 'Publish Art' --project=chromium

```

**Safari**
```
npx playwright test -g 'Publish Art' --project=webkit
```
**Mobile ViewPort**
```
npx playwright test -g 'Publish Art' --project='mobile chrome'
```
To run all test cases on all browsers in sequential manner,

In playwright.config.ts file we need to set `workers: 1`
 
Then run below command:
```
npx playwright test -g 'Publish Art'
```
To run all test cases on all browsers in sequential manner but on multiple workers,

In playwright.config.ts file we need to comment `//workers: 1` and  ` fullyParallel:false`
 
Then run below command:
```
npx playwright test -g 'Publish Art'
```
To run all test cases on all browsers in parallel/random manner with multiple workers,

In playwright.config.ts file we need to make ` fullyParallel:true` and comment `//workers: 1`

 ![image](https://github.com/keshavpokhrel/AirlineTicketingDemo/assets/6346814/8de8bfae-bcc5-4658-8743-bee016b537d2)

Then run below command:
```
npx playwright test -g 'Publish Art'
```
