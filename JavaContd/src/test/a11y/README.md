# Accessibility Testing

Accessibility testing is a vital step of the SDLC. In this implementation, I am using Axe HTML Reporter.

## From [NPM Docs](https://www.npmjs.com/package/axe-html-reporter)
Creates an HTML report from axe-core library AxeResults object listing violations, passes, incomplete and incompatible results.

Allows specifying report creation options: `reportFileName, outputDir, outputDirPath, projectKey and customSummary`.

Notes:

`customSummary` allows having html parameters
`outputDirPath` allows specifying absolute path
Please check sample report output.

`createHtmlReport` returns HTML content that can be additionally used for specific integrations.

If only HTML content needed, user can pass `doNotCreateReportFile: true` to stop report file creation.

Suggestion on how to use this library if you don't need a report file but need only HTML it produces:

    `const reportHTML = createHtmlReport({
        `results: rawAxeResults,
        `options: {
            `projectKey: 'I need only raw HTML',
            `doNotCreateReportFile: true,
        `},
    `});
    `console.log('reportHTML will have full content of HTML file.');
    `// suggestion on how to create file by yourself
    `if (!fs.existsSync('build/reports/saveReportHere.html')) {
        `fs.mkdirSync('build/reports', {
            `recursive: true,
        `});
    `}
    `fs.writeFileSync('build/reports/saveReportHere.html', reportHTML);

## From a [Blog Post](https://dev.to/auraswap/html-reporting-for-axecore-53ed) by one of my [Amazing Co-Workers](https://dev.to/auraswap)
I couldn't find a good step-by-step ReadMe that worked for my needs. 
So, I'm here to tell you how I implemented this HTML report to my AxeCore JSON output and, maybe it'll work for your needs too!
I'll also add that, this is to produce one HTML report and rewrite the report on each test run.

### Steps

1. Install Axe HTML Reporter (see NPM Docs above)
2. Update your configurations needed for your project
   1. `createHtmlReport` in AxeHTMLReport.js file
   2. `options` in AxeHTMLReport.js file
   3. Steps in .java file
3. Run tests via Maven

### Other Configuration Options
#### Example usage in any JS framework
`import { createHtmlReport } from 'axe-html-reporter';

`(() => {
    // creates html report with the default name `accessibilityReport.html` file
    createHtmlReport({ results: 'AxeResults' }); // full AxeResults object
    // creates html report with the default name `accessibilityReport.html` file and all supported AxeResults values
    createHtmlReport({ results: { violations: 'Result[]' } }); // passing only violations from axe.run output
    // creates html report with the default name `accessibilityReport.html` file and adds url and projectKey
    createHtmlReport({
        results: 'AxeResults',
        options: { projectKey: 'JIRA_PROJECT_KEY' },
    });
    // creates html report with the name exampleReport.html in axe-reports directory and adds projectKey to the header
    createHtmlReport({
        results: 'AxeResults',
    options: {
        projectKey: 'JIRA_PROJECT_KEY',
        outputDir: 'axe-reports',
        reportFileName: 'exampleReport.html',
        },
    });
    `// creates html report with all optional parameters, saving the report into 'docs' directory with report file name 'index.html'
    `const customSummary = Test Case: Full page analysis
    < br >Steps:< / br >
    < ol style="margin: 0" >
    < li >Open https://dequeuniversity.com/demo/mars/</ li >
    < li >Analyze full page with all rules enabled</ li >
    < /ol >;

    `createHtmlReport({
    `results: 'AxeResults',
    `options: {
        `projectKey: 'DEQUE',
        `customSummary,
        `outputDir: 'docs',
        `reportFileName: 'index.html',
        `},
    `});
    `})();`

#### CommonJS
`const { createHtmlReport } = require('axe-html-reporter');

`(() => {
    `// creates html report with the name `accessibilityReport.html` file
    `createHtmlReport({ results: { violations: 'Result[]' } });
`})();