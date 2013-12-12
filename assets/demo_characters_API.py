'''
Note pour les Brakassey:
C'est un script Python que j'avais codé avec Blender pour faire un combat comme un Final Fantasy.
Je ne sais pas si ça sera utile, au cas où.
Ça pourrait au moins être utile pour implémenter les personnages, sauf si vous avez une meilleure
façon de faire !

Le code est assez commenté, mais je pourrai me charger de le faire en Java après le 20/12/13.
Je laisse ça ici pour le retrouver facilement au prochain "git pull".
'''




'''
Class_Character.py
@brief  This file contains all the classes requires to make
        a minimalist RPG fighting system.
Copyright (C) 2013  Blender Jungle (bibi09)
http://blender-jungle.tuxfamily.org

Blender 2.67 and next recommended!
'''



from bge import logic, types
from abc import ABCMeta, abstractmethod
import random


'''
@brief  A Gauge that is filled gradually.
'''
class GaugeTimer :

    '''
    @brief  Create a new GaugeTimer.
    @param  iWait   Maximal value of the gauge.
    @param  iInit   Initial filling of the gauge.
    '''
    def __init__(self, iMax) :
        assert iMax > 1 ;

        self._iInitValue = iMax ;
        self._iCounter = random.randint(0, iMax - 1) ;
    # End of __init__()

    '''
    @brief  Tell if the programmed time was reached. The counter is
            automatically reset when time is reached and the count
            restarts.
    @param value		Value to modify the gauge state.
    @return TRUE if the timer reached the end. FALSE else.
    '''
    def isReady(self, iValue) :
        assert iValue > -1 ;
        assert iValue < self._iInitValue ;

        self._iCounter += iValue ;
        if (self._iCounter > self._iInitValue) :
#            self._iCounter = 0 ;
            return True ;
        return False ;
    # End of isReady()


    '''
    @brief  Manually reinitialize the timer.
    '''
    def reinit(self) :
        self._iCounter = 0 ;
    # End of reinit()

    '''
    @brief  Get ratio actual/max.
    '''
    def getRatio(self) :
        return float(self._iCounter) / float(self._iInitValue) ;
    # End of getRation()


'''
@brief  Statistics of a character are all the data that can
        make it evolve : life, strength, defense...
@warning Here this is a basic implementation with only velocity.
'''
class Statistics :
    '''
    @brief  Create a Statistics object.
    @param  iVelocity   Velocity of the character.
    '''
    def __init__(self) :
        self._iHPMax = random.randint(400, 999) ;   # Max amount of life
        self._iHPCur = self._iHPMax ;               # Current amount of life
        self._iStr = random.randint(100, 255) ;     # Strength
        self._iVel = random.randint(100, 255) ;     # Velocity
    # End of __init__()

    '''
    @brief  The character is damaged.
    @param  iDamage Amount of damages caused to the character.
    '''
    def damage(self, iDamages) :
        self._iHPCur -= iDamages ;
        if self._iHPCur < 0 :
            self._iHPCur = 0 ;
    # End of damage()

    '''
    @brief  Get the HP max of the character.
    @return HP max of the character.
    '''
    def getHPMax(self) :
        return self._iHPMax ;
    # End of getHPMax()

    '''
    @brief  Get the current amount of HP of the character.
    @return Current amount of HP of the character.
    '''
    def getHPCur(self) :
        return self._iHPCur ;
    # End of getHPCur()

    '''
    @brief  To know if the character is KO.
    @return TRUE if KO, else if alive.
    '''
    def isKO(self) :
        return self._iHPCur == 0 ;
    # End of isKO()

    '''
    @brief  Get damages caused by a character.
    @return Damages caused by a character.
    '''
    def getDamages(self) :
        return random.randint(1, self._iStr) ;
    # End of getDamages() ;

    '''
    @brief  Get the velocity of the character.
    @return Velocity of the character.
    '''
    def getVelocity(self) :
        return self._iVel ;
    # End of getVelocity()


    '''
    @brief  Export HP status as text
    @return Text of the HP...
    '''
    def HPtoText(self) :
        text = 'HP ' ;
        text += str(self._iHPCur) ;
        if self._iHPCur < 100 :
            text = ' ' + text ;
            if self._iHPCur < 10 :
                text = ' ' + text ;
        text += '/' + str(self._iHPMax) ;
        return text ;
    # End of HPtoText()

    '''
    @brief  String representation of Character.
    @return The String representation of the Character.
    '''
    def __str__(self) :
        return '[HP: ' + str(self._iHPCur) + '/' + str(self._iHPMax) + ' | Str: ' + str(self._iStr) + ' | Vel: ' + str(self._iVel) + ']' ;
    # End of __str__()



'''
@brief  A Character do nothing when ready but the behaviour
        can be customized into inherited classes (see the
        action() method).
@version 1.0
'''
class Character(types.KX_GameObject) :
    ''' @brief  List of all the Characters. '''
    AllCharacters = set() ;

    ''' @brief  To know if another character is already attacking. '''
    SomeoneDoingAction = False ;

    ''' @brief FIFO list of characters waiting for fighting. '''
    WaitingCharacters = [] ;

    ''' @brief  Maximal size of the attack gauge. '''
    _MAX_ACTION_GAUGE = 65535 ;

    '''
    @brief  Create a Character.
    @param  sName   The name of the character.
    '''
    def __init__(self, object) :
        super().__init__() ;

        # Add the new character to the list of characters
        size = len(Character.AllCharacters) + 1 ;
        Character.AllCharacters.add(self) ;
        assert len(Character.AllCharacters) == size ;

        # Aha, because it was not expected to be so "complete"...
        self._sName = self.name ;
        self._oStats = Statistics() ;

        # Set the ATB-like gauge
        iInitGauge = random.randint(0, Character._MAX_ACTION_GAUGE) ;
        self._oActionGauge = GaugeTimer(Character._MAX_ACTION_GAUGE) ;
        self._bActionDone = True ;
        self._oActionGauge3D = logic.HUDScene.objects['ATB' + str(size)] ;
        self._oActionGauge3D.worldScale = [self._oActionGauge.getRatio(), 1, 1] ;

        self._oHPText3D = logic.HUDScene.objects['Char_HP' + str(size)] ;
        self._oHPText3D.text = self._oStats.HPtoText() ;
        logic.HUDScene.objects['Char_Name' + str(size)].text = self._sName ;
    # End of __init__()

    '''
    @brief  Destruction of the Character object.
    '''
    def __des__(self) :
        Character.AllCharacters.remove(self) ;
    # End of __des__()


    '''
    @brief  Tic the character, filling its action gauge.
    '''
    def _tic(self) :
        WaitList = Character.WaitingCharacters ;

        # If the character is not waiting for its action, fill the gauge
        if self._bActionDone :
            iVelocity = self._oStats.getVelocity() ;
            if self._oActionGauge.isReady(iVelocity) :
                self._bActionDone = False ; # The character must wait...
    
                # If no one is doing an action or 
                if (not Character.SomeoneDoingAction) :
                    self._makeAction() ;    # OK, nobody else!
                elif WaitList.count(self) == 0 :
                    WaitList.append(self) ; # Wait in the FIFO list
        elif WaitList.count(self) == 1 :
            condition = (not Character.SomeoneDoingAction) ;
            condition &= WaitList.index(self) == 0 ;
            if condition :
                self._makeAction() ;

        # Update the HUD
        self._oActionGauge3D.localScale = [self._oActionGauge.getRatio(), 1, 1] ;
    # End of _tic()


    '''
    @brief  Manage the starting of the action.
    '''
    def _makeAction(self) :
        WaitList = Character.WaitingCharacters ;

        Character.SomeoneDoingAction = True ;
        self._action() ;
        if WaitList.count(self) == 1 :
            WaitList.remove(self) ;
    # End of _makeAction()


    '''
    @brief  Action to do when the character is ready.
            This is to be defined in subclasses.
    '''
    @abstractmethod
    def _action(self) :
        pass
    # End of _action()

    '''
    @brief  Signal to characters when the previous one
            has finish its action (end of animation, etc).
    '''
    def signalActionEnd(self) :
        Character.SomeoneDoingAction = False ;
        self._bActionDone = True ;
        self._oActionGauge.reinit() ;
    # End of signalActionEnd()

    '''
    @brief  Damage another character.
    @param  other   The other character to damage.
    '''
    def _damage(self, other) :
        iDamages = self._oStats.getDamages() ;
        other._oStats.damage(iDamages) ;
        other._oHPText3D.text = other._oStats.HPtoText() ;
    # End of damage


    '''
    @brief  To know if the character is KO.
    @return TRUE if KO, else if alive.
    '''
    def isKO(self) :
        return self._oStats.isKO() ;
    # End of isKO()
        

    '''
    @brief  Tick all the characters, filling their action gauge.
    '''
    @staticmethod
    def ticAll() :
        for oCharacter in Character.AllCharacters :
            oCharacter._tic() ;
    # End of ticAll()


    '''
    @brief  Give a custom name to the character.
    @param  sName   A new name to the character.
    '''
    def setName(self, sName) :
        self._sName = str(sName) ;
    # End of setName()


    '''
    @brief  String representation of Character.
    @return The String representation of the Character.
    '''
    def __str__(self) :
        return self._sName + ' ' + str(self._oStats) ;



'''
@brief  A ConcreteCharacter can attack an enemy.
'''
class ConcreteCharacter(Character) :

    '''
    @brief  Create a Character.
    @param  sName   The name of the character.
    '''
    def __init__(self, object) :
        super().__init__(object) ;
        self._oEnemy = None ;
    # End of __init__()

    '''
    @brief  Set the enemy to fight...
    '''
    def setEnemy(self, oEnemy) :
        try :
            # Try to access a Character object attribute
            oEnemy._oStats ;
            self._oEnemy = oEnemy ;
        except :
            print('The enemy is not of expected object type...') ;
    # End of setEnemy()

    '''
    @brief  Action to do when the character is ready.
            This is to be defined in subclasses.
    '''
    def _action(self) :
        if (self._oEnemy is not None) and (not self._oEnemy.isKO()) :
            print(self._sName + " attacks " + self._oEnemy._sName) ;
            self['TriggerAction'] = 1 ;
            self._damage(self._oEnemy) ;
        else :
            print(self._sName + " has no enemy...") ;
    # End of _action()


    '''
    @brief  Signal to characters when the previous one
            has finish its action (end of animation, etc).
    '''
    def signalActionEnd(self) :
        super().signalActionEnd() ;
        self['TriggerAction'] = 0 ;
        self['ActualFrame'] = 0 ;
        self.sensors['Waiting'].reset() ;
    # End of signalActionEnd()
