module.exports = {
  moduleNameMapper: {
    "@core/(.*)": "<rootDir>/src/app/core/$1",
  },
  preset: "jest-preset-angular",
  reporters: ["default", "jest-junit"],
  setupFilesAfterEnv: ["<rootDir>/setup-jest.ts"],
  globals: {
    // ...
    crypto: require("crypto"),
  },
};
