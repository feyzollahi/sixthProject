import React, { Component, constructor } from 'react';
import './App.css';
import UserSkillItem from './UserSkillItem'
import Footer from './Footer';
import Header from './Header';
// import './bootstrap.css';
import faker from 'faker';
import axios from 'axios'
import { number } from 'prop-types';
class App extends Component<any, State> {
    static skillItemsJSX: any;


  constructor(props: any){
      super(props);
      this.state = {uskills: [], bio: "", id: "", firstName: "", lastName: "", jobTitle: "", skillItemJSX: null};
      var userData:string;
        var userDataJson:any;
        var self = this;
        axios.get("http://localhost:8080/showSpecifiedUserCtrl",{
            params: {
                userId: "4"
            }
        })
        .then(function(resp){
            console.log(resp);
            if(resp.status == 200){
                userData = resp.data;
                console.log(userData);
                userDataJson = userData;
                console.log(userData);
                self.setState({firstName: userDataJson.firstName, lastName: userDataJson.lastName
                , bio: userDataJson.bio, jobTitle: userDataJson.jobTitle, id: userDataJson.id
                , uskills: userDataJson.uSkills});
                App.skillItemsJSX = [];
                self.state.uskills.forEach(function(uskill){
                    App.skillItemsJSX.push(<UserSkillItem skillName={uskill.name} skillRate={uskill.endorsedCount} isEndorsedNow={false} userId={self.state.id}/>);
                });
                self.setState({skillItemJSX: App.skillItemsJSX});
                
            }
        }).catch(error => {
            console.log(error)
        });
  }
  render() {
    
      var skillListItem = <li className="skill-item">
            <span className="skill-name">HTML</span>
            <span className="skill-rating">
                <span>5</span>
                <span className="skill-endorse">+</span>
            </span>
        </li>;
        
        
    return (
          
        <div>
        <meta charSet="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta httpEquiv="X-UA-Compatible" content="ie=edge" />
        <link rel="old stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossOrigin="anonymous" />

        <link rel="stylesheet" href="/assets/fonts/flaticons/flaticon.css" />
        <link rel="new stylesheet" href="./user.css" />
        <title>پروفایل محمدرضا کیانی</title>
        <Header/>
        <main>
          <section id="slider">
            <div className="slider-container container">
            </div>
          </section>
          <div id="wrapper" className="container">
            <div className="user-header-wrapper">
              <div className="user-header-container">
                <div className="user-avatar">
                  <img src={faker.image.avatar()} alt="s" />
                </div>
                <div className="user-content">
                  <h3 className="user-name">{this.state.firstName +" " + this.state.lastName}</h3>
                  <span className="user-nickname">اعلی حضرت</span>
                </div>
              </div>
            </div>
            <div className="user-description">
              <p>{this.state.bio}</p>
            </div>
            <div className="user-skills">
              <ul className="skills-list">
                <li className="skill-item endorsed">
                  <span className="skill-name">CSS</span>
                  <span className="skill-rating">
                    <span>3</span>
                  </span>
                </li>
                {App.skillItemsJSX}
              </ul>
            </div>
          </div>
        </main>
        <Footer/>
      </div>
      
      
      
    );
  }
}
interface State{
    uskills: USkill[];
    id: string;
    jobTitle: string;
    bio: string;
    firstName: string;
    lastName: string;
    skillItemJSX: any;
}
interface USkill{
    endorsedCount: number;
    name: string;
    endorser: State;
}
export default App;
