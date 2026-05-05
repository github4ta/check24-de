# check24-de/js

## Local setup

Prerequisites:
- Node.js 18+ installed
- npm available in your PATH

Install dependencies:

```bash
npm install
```

## Run tests

Execute WebdriverIO using the configured runner:

```bash
npm run wdio
```

## Project structure

- `wdio.conf.js` — WebdriverIO configuration
- `test/pageobjects/` — page object models
- `test/specs/` — E2E test specs

## Notes

- The project uses the `@wdio/cli`, `@wdio/local-runner`, `@wdio/mocha-framework`, and `@wdio/spec-reporter` packages.
- If you need to run a specific spec, modify `wdio.conf.js` or pass a spec pattern via CLI.
