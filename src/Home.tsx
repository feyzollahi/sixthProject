import React, { Component } from 'react'
import Header from './Header';
import Footer from './Footer';
import './Home.css';
import './App.css';
import ProjectSummaryItem from './ProjectSummaryItem';
import UserSummaryItem from './UserSummaryItem';
export default class Home extends Component<any, State> {
    constructor(props: any) {
      super(props);
        var persianJs = require('persianjs');
        var firstPart = persianJs("جاب اونجا").halfSpace().toString();
        console.log(firstPart);
        var jobOonjaHeader:string = firstPart + "  خوب است!";
        console.log(jobOonjaHeader);
      this.state = {
         jobOonjaHeader: jobOonjaHeader
      }
    }
    
  render() {
    return (
        <div>
        <meta charSet="UTF-8" />
        {/*<link rel="stylesheet" type="text/css" href="fonts/iransans-fonts/fonts.scss">*/}
        <link rel="stylesheet" type="text/css" href="../signUp/signUp.css" />
        <link rel="stylesheet" type="text/css" href="home.css" />
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css" />
        <title>Title</title>
        <div style={{minHeight: '100vh'}}>
          <Header/>
          <div className="jobOonjaHomeMiddleBackGround">
            <div className="container">
                <div className="jobOonjaHomeBackGroundParagraphDiv">
                    <p className="jobOonjaHomeBackGroundParagraph">{this.state.jobOonjaHeader}</p>
                </div>
                <div className="searchInputDiv">
                  <div className="input-container">
                      <input type="text" className="searchInput" onFocus={(e) => {e.target.placeholder = ""}} onBlur={(e) => {e.target.placeholder = "جستجو در جاب اونجا";}} placeholder="جستجو در جاب اونجا" />
                      <div className="submitBuutonDiv">
                        <input type="submit" value="جستجو" className="searchButton" />
                      </div>
                  </div>
                </div>
            </div>
          </div>
          <div className="user-project-list">
            <div className="user-summary-list">
              <UserSummaryItem name="مهدی فیض الهی" jobTitle="برنامه نویس"/>
              <UserSummaryItem name="مهدی فیض الهی" jobTitle="برنامه نویس"/>
              <UserSummaryItem name="مهدی فیض الهی" jobTitle="برنامه نویس"/>

            </div>
            <div className="project-summary-list">
              <ProjectSummaryItem deadline={1} budget={1} id="" description="" title="" skills={["", ""]} imageUrlText=""/>
              <ProjectSummaryItem deadline={1} budget={1} id="" description="" title="" skills={["", ""]} imageUrlText=""/>
              <ProjectSummaryItem deadline={1} budget={1} id="" description="" title="" skills={["", ""]} imageUrlText=""/>
              <ProjectSummaryItem deadline={1} budget={1} id="" description="" title="" skills={["", ""]} imageUrlText=""/>

            </div>
          </div>
          <Footer/>
        </div>
      </div>
    )
  }
}

interface State{
    jobOonjaHeader:string;
}
