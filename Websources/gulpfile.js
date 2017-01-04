(function (){
    'use strict';
    
    // Declare our variables
    var buildCss,
        partialCss,
        finCss,
        distCss,
        buildJs,
        finJs,
        minJs,
        distMinJs,
        distJs,
//        controllerJs,
        gulp,
        jshint,
        sass,
        concat,
        uglify,
        rename,
        prefix,
        server,
        source,
        sourcemaps,
        buffer,
        gutil,
        stream,
        copyDist,
        typeBrowserRef

    //Instantiate Variables
        // Build CSS
    var buildCss = 'src/sass/*.scss', 
        partialCss = 'src/sass/partials/*',
        // Finished Concat + Minify CS, 
        finCss = 'style.min.css',
        // Distribute CS, 
        distCss = 'app/css',

        // Build JS, 
        buildJs = 'src/js/*.js',
        
        // Finished Concat + Minify JS, 
        finJs = 'final.js', 
        minJs = 'final.min.js',
        
        // Distribute JS, 
        distMinJs = 'app/js/final.min.js', 
        distJs = 'app/js/ux',

        // Angular 2 shims location, 
        copyDist = 'app/lib',
        
        typeBrowserRef = "node_modules/angular2/typings/browser.d.ts",

        // Include Our Plugin, 
        gulp = require("gulp"),
        source = require('vinyl-source-stream'), 
        sourcemaps = require('gulp-sourcemaps'),
        typescript = require('gulp-typescript'),
        tscConfig = require('./tsconfig.json'),
        buffer = require('vinyl-buffer'),
        uglify = require("gulp-uglify"),
        gutil = require("gulp-util"), 
        stream = require("gulp-streamify"),
        jshint = require('gulp-jshint'), 
        sass = require('gulp-sass'), 
        concat = require('gulp-concat'), 
        uglify = require('gulp-uglify'), 
        rename = require('gulp-rename'), 
        prefix = require('gulp-autoprefixer')
    
    // Copy Angular 2 shims to dist/lib
    gulp.task('copylibs', function() {
      return gulp
        .src([
          'node_modules/es6-shim/es6-shim.min.js',
          'node_modules/systemjs/dist/system-polyfills.js',
          'node_modules/angular2/bundles/angular2-polyfills.js',
          'node_modules/systemjs/dist/system.src.js',
          'node_modules/rxjs/bundles/Rx.js',
          'node_modules/angular2/bundles/angular2.dev.js'
        ])
        .pipe(gulp.dest(copyDist));
    });

    // Lint Task
    gulp.task('lint', function() {
        return gulp.src([buildJs])
            .pipe(jshint())
            .pipe(jshint.reporter('default'));
    });

    // Compile Our Sass
    gulp.task('sass', function() {
        return gulp.src([buildCss, partialCss])
            .pipe(sass({outputStyle: 'compressed'})).on('error', sass.logError)
            .pipe(concat(finCss))
            .pipe(prefix({
      browsers: ['last 2 versions', '> 5%', 'Firefox ESR']
    }))
            .pipe(gulp.dest(distCss));
    });

    // Concatenate & Minify JS
    gulp.task('scripts', function() {
        return gulp.src(buildJs)
            .pipe(concat(finJs))
            .pipe(gulp.dest(distJs))
            .pipe(rename(minJs))
            .pipe(uglify())
            .pipe(gulp.dest(distJs));
    });

    // Watch Files For Changes
    gulp.task('watch', function() {
        gulp.watch([buildJs, distMinJs], ['lint', 'scripts']);
        gulp.watch([buildCss, partialCss], ['sass']);
    });

    gulp.task("default", ['lint', 'sass', 'watch']);

}());